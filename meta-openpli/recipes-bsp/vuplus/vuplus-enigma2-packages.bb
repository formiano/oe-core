DESCRIPTION = "Vuplus Specific plugins"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c9e255efa454e0155c1fd758df7dcaf3"
SRCREV_pn-${PN} ?= "${AUTOREV}"
DEPENDS = "python-native"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit gitpkgv
 
PV = "experimental-git${SRCPV}"
PKGV = "experimental-git${GITPKGV}"
PR = "r2"
BRANCH = "vuplus_experimental"

SRC_URI = "git://code.vuplus.com/git/dvbapp.git;protocol=http;branch=${BRANCH}"
SRC_URI += "file://3g.patch \
	file://dlna.patch \
	"

PACKAGES =+ "${PN}-src"

inherit pkgconfig

FILES_${PN}-dbg += "\
	/usr/lib/enigma2/python/Plugins/*/*/.debug \
	/usr/lib/enigma2/python/Plugins/*/*/*/.debug \
	/usr/lib/enigma2/python/Plugins/*/*/*/*/.debug \
	/usr/lib/enigma2/python/Plugins/*/*/*/*/*/.debug \
	/usr/lib/enigma2/python/Plugins/*/*/*/*/*/*/.debug \
	"

FILES_${PN}-src = "\
	/usr/lib/enigma2/python/*/*.py \
	/usr/lib/enigma2/python/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*/*.py \
	/usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*/*/*.py \
	"

S = "${WORKDIR}/git"

python populate_packages_prepend () {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)

	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True)
}

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager/script
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint
	install -d  ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/DeviceManager
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/WebBrowser
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/WebBrowser/desc
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/WebBrowser/keymap
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV/icons
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNABrowser
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer

	install -m 0644 ${S}/lib/python/Plugins/SystemPlugins/3GModemManager/*.py \
	${S}/lib/python/Plugins/SystemPlugins/3GModemManager/*.xml \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager

	install -m 0755 ${S}/lib/python/Plugins/SystemPlugins/3GModemManager/3gcommand \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager

	install -m 0755 ${S}/lib/python/Plugins/SystemPlugins/3GModemManager/script/ppp-stop \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager/script

	install -m 0644 ${S}/lib/python/Plugins/SystemPlugins/WirelessAccessPoint/*.py \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint

	install -m 0755 ${S}/lib/python/Plugins/SystemPlugins/WirelessAccessPoint/_wirelessap.so \
	${S}/lib/python/Plugins/SystemPlugins/WirelessAccessPoint/hostapd.conf.orig \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint

	install -m 0644 ${S}/lib/python/Plugins/SystemPlugins/DeviceManager/*.py \
	${D}/usr/lib/enigma2/python/Plugins/SystemPlugins/DeviceManager

	install -m 0644 ${S}/lib/python/Plugins/Extensions/WebBrowser/*.py \
	${S}/lib/python/Plugins/Extensions/WebBrowser/mp_wb_background.png \
	${S}/lib/python/Plugins/Extensions/WebBrowser/mp_wb_buttons.png \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/WebBrowser

	install -m 0644 ${S}/lib/python/Plugins/Extensions/WebBrowser/desc/*.png \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/WebBrowser/desc

	install -m 0755 ${S}/lib/python/Plugins/Extensions/WebBrowser/keymap/*.qmap \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/WebBrowser/keymap

	install -m 0644 ${S}/lib/python/Plugins/Extensions/StreamTV/*.py \
	${S}/lib/python/Plugins/Extensions/StreamTV/channel_background.png \
	${S}/lib/python/Plugins/Extensions/StreamTV/*.xml \	
	${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV

	install -m 0644 ${S}/lib/python/Plugins/Extensions/StreamTV/icons/*.png \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/StreamTV/icons

	install -m 0644 ${S}/lib/python/Plugins/Extensions/DLNABrowser/*.py \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNABrowser

	install -m 0644 ${S}/lib/python/Plugins/Extensions/DLNAServer/*.py \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer

	install -m 0755 ${S}/lib/python/Plugins/Extensions/DLNAServer/dlnaserver \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer

	python -O -m compileall ${D}/usr/lib/enigma2/python/Plugins/
}

FILES_enigma2-plugin-systemplugins-3gmodemmanager = "/usr/lib/enigma2/python/Plugins/SystemPlugins/3GModemManager"
FILES_enigma2-plugin-systemplugins-wirelessaccesspoint = "/usr/lib/enigma2/python/Plugins/SystemPlugins/WirelessAccessPoint"
FILES_enigma2-plugin-systemplugins-devicemanager = "/usr/lib/enigma2/python/Plugins/SystemPlugins/DeviceManager"
FILES_enigma2-plugin-extensions-webbrowser = "/usr/lib/enigma2/python/Plugins/Extensions/WebBrowser"
FILES_enigma2-plugin-extensions-streamtv = "/usr/lib/enigma2/python/Plugins/Extensions/StreamTV"
FILES_enigma2-plugin-extensions-dlnabrowser = "/usr/lib/enigma2/python/Plugins/Extensions/DLNABrowser"
FILES_enigma2-plugin-extensions-dlnaserver = "/usr/lib/enigma2/python/Plugins/Extensions/DLNAServer"

PACKAGES = "\
	enigma2-plugin-systemplugins-3gmodemmanager \
	enigma2-plugin-systemplugins-wirelessaccesspoint \
	enigma2-plugin-systemplugins-devicemanager \
	enigma2-plugin-extensions-webbrowser \
	enigma2-plugin-extensions-streamtv \
	enigma2-plugin-extensions-dlnabrowser \
	enigma2-plugin-extensions-dlnaserver \
	"

PROVIDES="${PACKAGES}"
