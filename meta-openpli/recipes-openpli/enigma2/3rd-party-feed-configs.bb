DESCRIPTION = "Configuration files for 3rd-party feeds"
PR = "r1"

require conf/license/openpli-gplv2.inc

FEEDS = "3rd-party 3rd-party-${MACHINE}"

do_compile() {
    mkdir -p ${S}/${sysconfdir}/opkg
    for feed in ${FEEDS}; do
        echo "src/gz ${DISTRO_FEED_PREFIX}-${feed} ${DISTRO_FEED_URI}/${feed}" > ${S}/${sysconfdir}/opkg/${feed}-feed.conf
    done
    echo "src/gz cam4me http://opencam.cam4me.org" > ${S}/${sysconfdir}/opkg/cam4me-feed.conf
}
do_install () {
        install -d ${D}${sysconfdir}/opkg
        install -m 0644 ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

CONFFILES_${PN} += '${@ " ".join( [ ( "${sysconfdir}/opkg/%s-feed.conf" % feed ) for feed in "${FEEDS}".split() ] ) }'
