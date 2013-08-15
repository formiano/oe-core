DESCRIPTION = "Ubuntu Font Family - TTF Version"
HOMEPAGE = "http://font.ubuntu.com"
require conf/license/license-gplv2.inc
PR = "r5"

SRC_URI = "http://font.ubuntu.com/download/ubuntu-font-family-${PV}.zip"

SRC_URI[md5sum] = "a1fc70f5a5b1d096ab8310886cddaa1c"
SRC_URI[sha256sum] = "107170099bbc3beae8602b97a5c423525d363106c3c24f787d43e09811298e4c"

S = "${WORKDIR}/ubuntu-font-family-${PV}"

inherit allarch

do_install(){
    install -d ${D}/usr/share/fonts
    install -m 0644 ${S}/*.ttf ${D}/usr/share/fonts
}

FILES_${PN} = "/usr/share/fonts"
