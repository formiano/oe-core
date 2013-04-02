DESCRIPTION = "PPTP Client is a Linux, FreeBSD, NetBSD \
and OpenBSD client for the proprietary Microsoft Point-to-Point \
Tunneling Protocol, PPTP. Allows connection to a PPTP based \
Virtual Private Network (VPN) as used by employers and some \
cable and ADSL internet service providers."
HOMEPAGE = "http://pptpclient.sourceforge.net"
SECTION = "network"
require conf/license/openpli-gplv2.inc
RDEPENDS_${PN} = "ppp (>= 2.4.5)"
PR = "r1"
PROVIDES = "pptp-linux"

SRC_URI = "${SOURCEFORGE_MIRROR}/sourceforge/pptpclient/pptp-${PV}.tar.gz \
           file://options.pptp"

S = "${WORKDIR}/pptp-${PV}"

do_compile() {
        oe_runmake
}
do_install() {
        install -d ${D}${sbindir} ${D}${sysconfdir}/ppp ${D}${mandir}/man8
        install -m 555 pptp ${D}${sbindir}
        install -m 644 pptp.8 ${D}${mandir}/man8
        install -m 644 ${WORKDIR}/options.pptp ${D}${sysconfdir}/ppp
}

SRC_URI[md5sum] = "4c3d19286a37459a632c7128c92a9857"
SRC_URI[sha256sum] = "e98ae0065d2a39fa3131654ff28cb7070e996f668ed6d0e7d9a445b8d37694bc"
