DESCRIPTION = "RSi Config by OpenRSi"
RDEPENDS = "enigma2"
DEPENDS = "python-native"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LICENSE = "CLOSED"

SRCREV_pn-${PN} ?= "${AUTOREV}"

inherit gitpkgv pkgconfig

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r1"

SRC_URI = "git://github.com/persianpros/rsiconfig-plugin.git;protocol=git"

S = "${WORKDIR}/git"

do_compile() {
	python -O -m compileall ${S}
}

python populate_packages_prepend () {
	enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)

	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.la$', 'enigma2-plugin-%s-dev', '%s (development)', recursive=True, match_path=True, prepend=True)
	do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.a$', 'enigma2-plugin-%s-staticdev', '%s (static development)', recursive=True, match_path=True, prepend=True)
}

do_install() {
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/RSIConfig
	install -d  ${D}/usr/lib/enigma2/python/Plugins/Extensions/RSIConfig/keymaps

	install -m 0644 ${S}/*.pyo \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/RSIConfig
	
	install -m 0755 ${S}/ntp* \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/RSIConfig
	
	install -m 0755 ${S}/dvb* \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/RSIConfig
	
	install -m 0755 ${S}/man* \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/RSIConfig
	
	install -m 0755 ${S}/keymaps/*.xml \
	${D}/usr/lib/enigma2/python/Plugins/Extensions/RSIConfig/keymaps
}

FILES_enigma2-plugin-extensions-rsiconfig = "/usr/lib/enigma2/python/Plugins/Extensions/RSIConfig"

PACKAGES = "enigma2-plugin-extensions-rsiconfig"

PROVIDES="${PACKAGES}"
