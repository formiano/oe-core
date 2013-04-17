python do_package_prepend () {
	boxtypes = [
		('azboxme', 'me.jpg', 'me.png'),
		('azboxminime', 'minime.jpg', 'me.png'),
		('azboxhd', 'premium.jpg', 'premium.png'),
	]
	import os
	top = '${D}${PLUGINPATH}/public/images/'
	for x in boxtypes:
		if x[0] == '${MACHINE}':
			target_box = x[1]
			target_remote = x[2]
			break
	for root, dirs, files in os.walk(top + 'boxes', topdown=False):
		for name in files:
			if target_box != name and name != 'unknown.jpg':
				if target_box == 'premium.jpg':
					if not (name == 'elite.jpg' or name == 'premium+.jpg' or name == 'ultra.jpg'):
						os.remove(os.path.join(root, name))
				else:
					os.remove(os.path.join(root, name))
	for root, dirs, files in os.walk(top + 'remotes', topdown=False):
		for name in files:
			if target_remote != name and name != 'ow_remote.png':
				if target_remote == 'premium.png':
					if not (name == 'elite.png'):
						os.remove(os.path.join(root, name))
				else:
					os.remove(os.path.join(root, name))
}
