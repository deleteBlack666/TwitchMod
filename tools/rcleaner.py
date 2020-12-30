import os
import re
import sys

import collections


CALLER_PATTERN = re.compile("^\s*[si](?:put|get)(?:-object)?.*,\s*(L[^;]+;)->(.*(?::\[?I))\s*$")
DEF_PATTERN = re.compile("^\.field public static final ([^ ]+:\[?I)(?:\s+=\s+0x[a-fA-F0-9]+)?")


def get_files(path):
	for root, dirs, files in os.walk(path):
		for file in files:
			if (file.endswith(".smali")):
				yield os.path.join(root, file)


def get_smali_files(path):
	for entry in os.listdir(path):
		if entry.startswith("smali_classes") or entry == "smali":
			smali_dir = os.path.join(path, entry)
			if os.path.isdir(smali_dir):
				yield from get_files(smali_dir)


def get_full_class(lines):
	return lines[0].strip().split(' ')[-1]


def is_r_class_name(class_line):
	if not class_line:
		return False

	name = class_line.split('/')[-1][:-1]
	if not name:
		return False

	if name.startswith("R$"):
		return True

	return False


def only_static_constructors(lines):
	for line in lines:
		if line.strip().startswith(".method"):
			method_name = line.split(" ")[-1]
			if not method_name.startswith('<init>'):
				return False

	return True


def is_r_file(lines):
	if len(lines) < 3:
		return False

	smali_class, smali_super, smali_source = lines[:3]

	if not is_r_class_name(smali_class):
		return False

	if smali_super != '.super Ljava/lang/Object;':
		return False

	if smali_source == '.source "R.java"':
		return True

	if not only_static_constructors(lines):
		return False

	# TODO: other checks

	return True


def parse_file(lines, callers):
	for line in lines:
		match = CALLER_PATTERN.search(line)
		if match:
			clazz = match.group(1)
			field = match.group(2)
			callers[clazz].add(field)


def clean(file, callers):
	file_path, lines = file

	clazz = get_full_class(lines)
	used_fields = callers[clazz]
	total_removed = 0
	skip_next_empty = False
	with open(file_path, 'w', encoding='utf-8') as fp:
		for line in lines:
			if skip_next_empty:
				if not line.strip():
					continue
				else:
					skip_next_empty = False

			match = DEF_PATTERN.search(line)
			if match:
				field = match.group(1)
				if not field in used_fields:
					total_removed += 1
					skip_next_empty = True
					continue
			fp.write("{}\n".format(line))

	return total_removed


if __name__ == '__main__':
	r_files = []
	callers = collections.defaultdict(set)

	total_r_files = 0
	total_files = 0
	print("Parsing...")
	for file in get_smali_files(os.path.abspath(sys.argv[1])):
		lines = []
		with open(file, 'r', encoding='utf-8') as fp:
			lines = fp.read().splitlines()

		if is_r_file(lines):
			total_r_files += 1
			r_files.append((file, lines))

		parse_file(lines, callers)
		total_files += 1

	print("R files: {}, total: {}".format(total_r_files, total_files))

	for file in r_files:
		removed = clean(file, callers)
		print("{} [{}]".format(file[0], removed))
