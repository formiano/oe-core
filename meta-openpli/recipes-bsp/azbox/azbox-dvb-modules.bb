DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.2-opensat"

SRCDATE = "22072013"
SRCDATE_azboxhd = "25072013"

PV = "${KV}+${SRCDATE}"
PR = "${AZVERSION}.${AZREVISION}"

SRC_URI = "http://azbox-enigma2-project.googlecode.com/files/${MACHINE}-dvb-modules-${KV}-oe-core-${SRCDATE}.tar.gz;name=azbox-dvb-modules-${MACHINE}"

SRC_URI[azbox-dvb-modules-azboxhd.md5sum] = "231d48a9c6a0a45ad50538e1ffc5804c"
SRC_URI[azbox-dvb-modules-azboxhd.sha256sum] = "182cd18a5c4dd0760127345ad0af0c88421fbbc9ae619c68dd0de6159ff8d398"
SRC_URI[azbox-dvb-modules-azboxme.md5sum] = "1383b477fbb55899d4907d455665bab2"
SRC_URI[azbox-dvb-modules-azboxme.sha256sum] = "91962bd365b7e8e3a4d9e3b97974907698be769324b3c53f87b5ef0d99302846"
SRC_URI[azbox-dvb-modules-azboxminime.md5sum] = "d179d7afa9ba9c3e9f05f87a5c3484c5"
SRC_URI[azbox-dvb-modules-azboxminime.sha256sum] = "9edc9ab92e6e8fd4ca2b0e3ad8b2f6a3c55341210048c6a593e38f6c06dbb741"

S = "${WORKDIR}"

PACKAGE_STRIP = "no"

inherit module

do_compile() {
}

do_install_azboxhd() {
    install -d ${D}/lib/modules/${KV}/extra
    install -d ${D}/${sysconfdir}/modutils

    for f in llad em8xxx 863xi2c az_cx24116 az_mxl201rf az_mxl5007t az_stv6110x az_stv090x az_tda10023 az_zl10353 nimdetect sci 863xdvb; do
	install -m 0644 ${WORKDIR}/$f.ko ${D}/lib/modules/${KV}/extra/$f.ko
	echo $f >> ${D}/${sysconfdir}/modutils/_${MACHINE}
    done

    install -d ${D}/lib/firmware
    install -m 0644 ${WORKDIR}/dvb-fe-cx24116.fw ${D}/lib/firmware/dvb-fe-cx24116.fw

}

do_install() {
    install -d ${D}/lib/modules/${KV}/extra
    install -d ${D}/${sysconfdir}/modutils

    for f in llad em8xxx 865xi2c avl6211 avl2108 mxl241sf nimdetect sci 865xdvb; do
	install -m 0644 ${WORKDIR}/$f.ko ${D}/lib/modules/${KV}/extra/$f.ko
	echo $f >> ${D}/${sysconfdir}/modutils/_${MACHINE}
    done

    install -d ${D}/lib/firmware
    install -m 0644 ${WORKDIR}/dvb-fe-avl2108.fw ${D}/lib/firmware/dvb-fe-avl2108.fw
    install -m 0644 ${WORKDIR}/dvb-fe-avl2108-blind.fw ${D}/lib/firmware/dvb-fe-avl2108-blind.fw
    install -m 0644 ${WORKDIR}/dvb-fe-avl6211.fw ${D}/lib/firmware/dvb-fe-avl6211.fw

}

FILES_${PN} = "/"
