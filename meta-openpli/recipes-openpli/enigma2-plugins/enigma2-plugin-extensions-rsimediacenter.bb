DESCRIPTION = "RSi Media Center by OpenRSi"
RDEPENDS = "enigma2"
DEPENDS = "python-native"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LICENSE = "CLOSED"

SRCREV_pn-${PN} ?= "${AUTOREV}"

inherit gitpkgv pkgconfig

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r2"

SRC_URI = "git://github.com/persianpros/rsimediacenter-plugin.git;protocol=git"

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

do_install_azboxhd() {
	install -d ${D}/usr/bin/
	install -m 0755 ${S}/bin/dvd_player-ForHD ${D}/usr/bin/dvd_player

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/
	install -m 0644 ${S}/plugin/*.pyo ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/
	install -m 0644 ${S}/plugin/*.png ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/
	install -m 0755 ${S}/plugin/*.so ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/
	install -m 0755 ${S}/plugin/*.xml ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/fonts/
        install -m 0644 ${S}/plugin/fonts/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/fonts/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/
        install -m 0644 ${S}/plugin/skins/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/
        install -m 0755 ${S}/plugin/skins/default/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/
        install -m 0644 ${S}/plugin/skins/default/images/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/buttons/
        install -m 0644 ${S}/plugin/skins/default/images/buttons/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/buttons/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/icons/
        install -m 0644 ${S}/plugin/skins/default/images/icons/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/icons/
}

do_install() {
	install -d ${D}/usr/bin/
	install -m 0755 ${S}/bin/dvd_player ${D}/usr/bin/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/
	install -m 0644 ${S}/plugin/*.pyo ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/
	install -m 0644 ${S}/plugin/*.png ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/
	install -m 0755 ${S}/plugin/*.so ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/

	install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/
	install -m 0755 ${S}/plugin/*.xml ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/fonts/
        install -m 0644 ${S}/plugin/fonts/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/fonts/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/
        install -m 0644 ${S}/plugin/skins/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/
        install -m 0755 ${S}/plugin/skins/default/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/
        install -m 0644 ${S}/plugin/skins/default/images/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/buttons/
        install -m 0644 ${S}/plugin/skins/default/images/buttons/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/buttons/

        install -d ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/icons/
        install -m 0644 ${S}/plugin/skins/default/images/icons/*.* ${D}/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/icons/
}

FILES_${PN} = "/usr/bin/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/fonts/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/buttons/"
FILES_${PN} += "/usr/lib/enigma2/python/Plugins/Extensions/MediaCenter/skins/default/images/icons/"
