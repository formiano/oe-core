DESCRIPTION = "Skins for Enigma2"
MAINTAINER = "schwerkraft"
PACKAGES = "${PN}-meta ${PN}"
PACKAGES_DYNAMIC = "enigma2-plugin-skins-*"

require conf/license/openpli-gplv2.inc

inherit gitpkgv

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "${AZVERSION}.${AZREVISION}"

SRC_URI = "git://github.com/OpenE2/enigma2-skins.git;protocol=git"

# note that enigma2-skins is just an empty package to satisfy silly dependencies.
ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = "/usr/share/enigma2 /usr/share/fonts"
FILES_${PN}-meta = "${datadir}/meta"
RDEPENDS_${PN}-meta = ""

inherit autotools

S = "${WORKDIR}/git"

python populate_packages_prepend () {
	if bb.data.expand('${REL_MINOR}', d) != "4":
		enigma2_skindir = bb.data.expand('${datadir}/enigma2', d)
		do_split_packages(d, enigma2_skindir, '(.*?)/.*', 'enigma2-plugin-skins-%s', 'Enigma2 Skin: %s', recursive=True, match_path=True, prepend=True)
}
