import os


MOD_RES_DIR_PATH = "mod/src/main/res"
APP_RES_DIR_PATH = "app/src/main/res"


def get_script_dir():
	return os.path.dirname(os.path.realpath(__file__))


def is_empty_xml(path):
	with open(path, 'r', encoding='utf-8') as f:
		return "<resources></resources>" in f.read()


def cl(path):
	for de in os.listdir(path):
		if not de.startswith("values-"):
			continue

		d = os.path.join(path, de)
		for file in os.listdir(d):
			file_path = os.path.join(d, file)
			if is_empty_xml(file_path):
				os.remove(file_path)
		if not os.listdir(d):
			os.rmdir(d)

if __name__ == '__main__':
	cl(os.path.join(os.path.dirname(get_script_dir()), MOD_RES_DIR_PATH))
	cl(os.path.join(os.path.dirname(get_script_dir()), APP_RES_DIR_PATH))
