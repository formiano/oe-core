DESCRIPTION = "tzcode, timezone zoneinfo utils -- zic, zdump, tzselect"
LICENSE = "PD"
PR = "r1"

LIC_FILES_CHKSUM = "file://${WORKDIR}/README;md5=d7a19b8c6d8a28785c4cd04ff2e46d27"

SRC_URI =" ftp://ftp.iana.org/tz/releases/tzcode${PV}.tar.gz;name=tzcode \
           ftp://ftp.iana.org/tz/releases/tzdata${PV}.tar.gz;name=tzdata"

SRC_URI[tzcode.md5sum] = "69d333d829802af4475707e32fa01681"
SRC_URI[tzcode.sha256sum] = "e46ee931927273108db1c6b5ab86c37210e903536a910b35a5699a08799bd6f0"
SRC_URI[tzdata.md5sum] = "b5062217a2dea6823dd69b4dda97b249"
SRC_URI[tzdata.sha256sum] = "e597a7eb239155eb2247fd4788f7fa10bf21d6469961200a6ca202f19cc15c87"


S = "${WORKDIR}"

inherit native

do_install () {
        install -d ${D}${bindir}/
        install -m 755 zic ${D}${bindir}/
        install -m 755 zdump ${D}${bindir}/
        install -m 755 tzselect ${D}${bindir}/
}
