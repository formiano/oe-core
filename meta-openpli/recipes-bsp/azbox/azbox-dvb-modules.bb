DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.9.2-opensat"

SRCDATE = "17092013"
SRCDATE_azboxhd = "19092013"

PV = "${KV}+${SRCDATE}"
PR = "${AZVERSION}.${AZREVISION}"

SRC_URI = "http://azbox-enigma2-project.googlecode.com/files/${MACHINE}-dvb-modules-${KV}-oe-core-${SRCDATE}.tar.gz;name=azbox-dvb-modules-${MACHINE}"

SRC_URI[azbox-dvb-modules-azboxhd.md5sum] = "0e9f349735ecae61fd9db76ea0da9985"
SRC_URI[azbox-dvb-modules-azboxhd.sha256sum] = "c12bf66416d1faf4c3efb35b590f02ee0c255879d057fa553fb95037138c2d75"
SRC_URI[azbox-dvb-modules-azboxme.md5sum] = "3d7b8d240626a08f16c170e5832be618"
SRC_URI[azbox-dvb-modules-azboxme.sha256sum] = "48b48a94094ecce34398efcee7e17e780d9cce0ecf1510758078ed4e18f9ce6d"
SRC_URI[azbox-dvb-modules-azboxminime.md5sum] = "2c037462af10a7909f4c803a90b82a1d"
SRC_URI[azbox-dvb-modules-azboxminime.sha256sum] = "7ac3c8ac567ffe627850fda0c6713dce71305886af6e3cc286800fbfc394709a"

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
