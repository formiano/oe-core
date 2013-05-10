DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = "3.3.1-opensat"

SRCDATE_azboxme = "10052013"
SRCDATE_azboxminime = "10052013"
SRCDATE_azboxhd = "20130315"


PV = "${KV}+${SRCDATE}"
MACHINE_KERNEL_PR_append = ".18"


SRC_URI = "http://azbox-enigma2-project.googlecode.com/files/${MACHINE}-dvb-modules-${KV}-oe-core-${SRCDATE}.tar.gz;name=azbox-dvb-modules-${MACHINE}"

SRC_URI[azbox-dvb-modules-azboxhd.md5sum] = "7fc3a0876eea4717b382d01ac7e68e10"
SRC_URI[azbox-dvb-modules-azboxhd.sha256sum] = "20b0416c2f7ace18cd30268f114eb99e3b64fa93f0cc2d9be030e7104ae085c3"
SRC_URI[azbox-dvb-modules-azboxme.md5sum] = "a18c3c691209e39c66d964970e7f5446"
SRC_URI[azbox-dvb-modules-azboxme.sha256sum] = "3a0542d67cc2283eff77fe12512216f6c011bb267d6ed66e2e35866d9fd489a1"
SRC_URI[azbox-dvb-modules-azboxminime.md5sum] = "d109572d82417978e0f25de49a9d2b03"
SRC_URI[azbox-dvb-modules-azboxminime.sha256sum] = "a6aaaa0f6054c1f4ba844da80e3264309005cffe137d4ac08dfc4b269646253f"

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

do_install_azboxme() {
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

do_install_azboxminime() {
 do_install_azboxme
}

FILES_${PN} = "/"


