PRINC = "5"

inherit openpli-distutils

do_install() {
	distutils_do_install_keep_pyo
}

PACKAGES =+ " ${PN}-src"
RDEPENDS_{PN}-src = "${PN}"
FILES_${PN}-src = " \
	${libdir}/${PYTHON_DIR}/site-packages/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/*/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/*/*/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/*/*/*/*/*.py \
	${libdir}/${PYTHON_DIR}/site-packages/*/*/*/*/*/*.py \
	"
