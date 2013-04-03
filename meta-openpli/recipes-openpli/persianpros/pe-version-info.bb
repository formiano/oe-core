DESCRIPTION = "PE version info"
SECTION = "base"
PRIORITY = "required"
require conf/license/openpli-gplv2.inc
MAINTAINER = "Persian Professionals <persianpros@yahoo.com>"

PV = "${AZVERSION}"
PR = "${AZREVISION}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

URL = "http://openazbox.info"

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
			echo "STB=${MACHINE}" > ${D}/etc/image-version
			echo "version=${AZVERSION}.${AZREVISION}" >> ${D}/etc/image-version
			echo "build=${AZREVISION}" >> ${D}/etc/image-version
			echo "OE=${AZOE}" >> ${D}/etc/image-version
			echo "Python=2.7" >> ${D}/etc/image-version
			echo "date=${DATETIME}" >> ${D}/etc/image-version
			echo "comment=Open AZBox" >> ${D}/etc/image-version
			echo "creator=Open AZBox - Persian Professionals" >> ${D}/etc/image-version
			echo "url=${URL}" >> ${D}/etc/image-version
}

FILES_${PN} += "/etc"

 
