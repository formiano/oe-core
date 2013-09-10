DESCRIPTION = "Requests: HTTP for Humans"
HOMEPAGE = "http://docs.python-requests.org/en/latest/"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
PR = "r3"

SRC_URI = "git://github.com/kennethreitz/requests;protocol=git"

S = "${WORKDIR}/git/"

inherit setuptools

# need to export these variables for python-config to work
export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

RDEPENDS_${PN} = "python"

BBCLASSEXTEND = "native"

LIC_FILES_CHKSUM = "file://README.rst;md5=5b5fc59487c805c8329067d134f0ba4e \
                    file://NOTICE;md5=78f5bacf7f1c81c3c798887c9e94d745 \
                    file://LICENSE;md5=c858703330162aa799141655a10fe120"
SRCREV = "v${PV}"
