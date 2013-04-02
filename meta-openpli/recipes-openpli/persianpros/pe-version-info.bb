DESCRIPTION = "PE version info"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "Persian Professionals <persianpros@yahoo.com>"

require conf/license/openpli-gplv2.inc

PV = "${PEVERSION}"
PR = "${PEREVISION}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI_append = "file://bootfix"

URL = "http://e2pe.com"

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
			echo "version=${PEVERSION}.${PEREVISION}" >> ${D}/etc/image-version
			echo "build=${PEREVISION}" >> ${D}/etc/image-version
			echo "OE=${PEOE} PE Mode" >> ${D}/etc/image-version
			echo "Python=2.7" >> ${D}/etc/image-version
			echo "date=${DATETIME}" >> ${D}/etc/image-version
			echo "comment=Persian Empire" >> ${D}/etc/image-version
			echo "creator=Persian Professionals" >> ${D}/etc/image-version
			echo "url=${URL}" >> ${D}/etc/image-version
}

FILES_${PN} = "/etc/image-version"

 
