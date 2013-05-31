DESCRIPTION = "AZ version info"
MAINTAINER = "RTi Team <rticore@gmail.com>"
LICENSE = "GPL2"
require conf/license/license-gplv2.inc

PV = "${AZVERSION}"
PR = "${AZREVISION}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI_append = "file://bootfix"

URL = "http://openazbox.info"

FILES_${PN} = "/etc"

INHIBIT_PACKAGE_STRIP = "1"

ALLOW_EMPTY_${PN} = "1"

S = "${WORKDIR}"

inherit autotools

PACKAGES = "${PN}"

do_install() {
			if [ "${DISTRO_TYPE}" = "experimental" ] ; then
				BUILDTYPE="1"
			else
				BUILDTYPE="0"
			fi

			install -d ${D}/etc
			# generate /etc/image-version
			echo "box_type=${MACHINE}" > ${D}/etc/image-version
			echo "build_type=${BUILDTYPE}" >> ${D}/etc/image-version			
			echo "STB=${MACHINE}" > ${D}/etc/image-version
			echo "version=${AZVERSION}.${AZREVISION}" >> ${D}/etc/image-version
			echo "build=${AZREVISION}" >> ${D}/etc/image-version
			echo "OE=${AZOE}" >> ${D}/etc/image-version
			echo "Python=2.7" >> ${D}/etc/image-version
			echo "date=${DATETIME}" >> ${D}/etc/image-version
			echo "comment=Open AZBox" >> ${D}/etc/image-version
			echo "target=9" >> ${D}/etc/image-version
			echo "creator=Open AZBox - Persian Professionals" >> ${D}/etc/image-version
			echo "url=${URL}" >> ${D}/etc/image-version
			echo "catalog=${URL}" >> ${D}/etc/image-version
			install -d ${D}${sysconfdir}/init.d
			install -d ${D}${sysconfdir}/rcS.d
			install -m 0755 ${WORKDIR}/bootfix	${D}${sysconfdir}/init.d
			ln -sf ../init.d/bootfix ${D}${sysconfdir}/rcS.d/S06bootfix
}
