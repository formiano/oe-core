DESCRIPTION = "Timezone data"
HOMEPAGE = "ftp://elsie.nci.nih.gov/pub/"
SECTION = "base"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://asia;beginline=2;endline=3;md5=06468c0e84ef4d4c97045a4a29b08234"
DEPENDS = "tzcode-native"

PR = "r1"

RCONFLICTS= "timezones timezone-africa timezone-america timezone-antarctica \
             timezone-arctic timezone-asia timezone-atlantic \
             timezone-australia timezone-europe timezone-indian \
             timezone-iso3166.tab timezone-pacific timezone-zone.tab"

SRC_URI = "ftp://ftp.iana.org/tz/releases/tzdata${PV}.tar.gz;name=tzdata"

SRC_URI[tzdata.md5sum] = "b5062217a2dea6823dd69b4dda97b249"
SRC_URI[tzdata.sha256sum] = "e597a7eb239155eb2247fd4788f7fa10bf21d6469961200a6ca202f19cc15c87"

S = "${WORKDIR}"

DEFAULT_TIMEZONE ?= "Iran"

TZONES= "africa antarctica asia australasia europe northamerica southamerica  \
         factory solar87 solar88 solar89 etcetera backward systemv \
        "
# pacificnew 

do_compile () {
        for zone in ${TZONES}; do \
            ${STAGING_BINDIR_NATIVE}/zic -d ${WORKDIR}${datadir}/zoneinfo -L /dev/null \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
            ${STAGING_BINDIR_NATIVE}/zic -d ${WORKDIR}${datadir}/zoneinfo/posix -L /dev/null \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
            ${STAGING_BINDIR_NATIVE}/zic -d ${WORKDIR}${datadir}/zoneinfo/right -L ${S}/leapseconds \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
        done
}

do_install () {
        install -d ${D}/$exec_prefix ${D}${datadir}/zoneinfo
        cp -pPR ${S}/$exec_prefix ${D}/
        # libc is removing zoneinfo files from package
        cp -pP "${S}/zone.tab" ${D}${datadir}/zoneinfo
        cp -pP "${S}/iso3166.tab" ${D}${datadir}/zoneinfo

        # Install default timezone
        install -d ${D}${sysconfdir}
        echo ${DEFAULT_TIMEZONE} > ${D}${sysconfdir}/timezone

        chown -R root:root ${D}
}

pkg_postinst_${PN} () {

# code taken from Gentoo's tzdata ebuild

	etc_lt="$D${sysconfdir}/localtime"
	src="$D${sysconfdir}/timezone"

	if [ -e ${src} ] ; then
		tz=$(sed -e 's:#.*::' -e 's:[[:space:]]*::g' -e '/^$/d' "${src}")
	else
		tz="FUBAR"
	fi
	
	if [ -z ${tz} ] ; then
		return 0
	fi
	
	if [ ${tz} = "FUBAR" ] ; then
		echo "You do not have TIMEZONE set in ${src}."

		if [ ! -e ${etc_lt} ] ; then
			# if /etc/localtime is a symlink somewhere, assume they
			# know what they're doing and they're managing it themselves
			if [ ! -L ${etc_lt} ] ; then
				cp -f "$D${datadir}/zoneinfo/Universal" "${etc_lt}"
				echo "Setting ${etc_lt} to Universal."
			else
				echo "Assuming your ${etc_lt} symlink is what you want; skipping update."
			fi
		else
			echo "Skipping auto-update of ${etc_lt}."
		fi
		return 0
	fi

	if [ ! -e "$D${datadir}/zoneinfo/${tz}" ] ; then
		echo "You have an invalid TIMEZONE setting in ${src}"
		echo "Your ${etc_lt} has been reset to Universal; enjoy!"
		tz="Universal"
	fi
	echo "Updating ${etc_lt} with $D${datadir}/zoneinfo/${tz}"
	if [ -L ${etc_lt} ] ; then
		rm -f "${etc_lt}"
	fi
	cp -f "$D${datadir}/zoneinfo/${tz}" "${etc_lt}"
}

# Packages primarily organized by directory with a major city
# in most time zones in the base package

PACKAGES = "tzdata"

FILES_${PN} += "${datadir}/zoneinfo/*"
