DESCRIPTION = "Linux kernel for ${MACHINE}"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${KV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
MACHINE_KERNEL_PR_append = ".13"

KV = "3.3.1"
SRCDATE = "09052013"

DEPENDS = "genromfs-native gcc"
DEPENDS_azboxhd = "genromfs-native azbox-hd-buildimage gcc"
DEPENDS_azboxminime = "genromfs-native azbox-minime-packer gcc"

SRC_URI += "http://azbox-enigma2-project.googlecode.com/files/linux-azbox-${KV}-new-2.tar.bz2;name=azbox-kernel \
	   file://${MACHINE}_defconfig \
	   file://genzbf.c \
	   file://sigblock.h \
	   file://zboot.h \
	   file://emhwlib_registers_tango2.h \
	   file://sata.patch \
	   "

SRC_URI_append_azboxhd += "http://azbox-enigma2-project.googlecode.com/files/initramfs-${MACHINE}-oe-core-${SRCDATE}.tar.bz2;name=azbox-initrd-${MACHINE} \
	   file://hdide.patch \
	   "

SRC_URI_append_azboxme = "http://azbox-enigma2-project.googlecode.com/files/initramfs-${MACHINE}-oe-core-${SRCDATE}.tar.bz2;name=azbox-initrd-${MACHINE}"

SRC_URI_append_azboxminime = "http://azbox-enigma2-project.googlecode.com/files/initramfs-${MACHINE}-oe-core-${SRCDATE}.tar.bz2;name=azbox-initrd-${MACHINE}"


SRC_URI[azbox-kernel.md5sum] = "dfd04abeaf3741b3d2a44428ca5aeaa1"
SRC_URI[azbox-kernel.sha256sum] = "31b73397220d85aedf3c914026371fc1eeac67e3de09a5610b70b209d2a8b9df"
SRC_URI[azbox-initrd-azboxhd.md5sum] = "072f993b5139a15bc63d2d99152c6da7"
SRC_URI[azbox-initrd-azboxhd.sha256sum] = "33064f25ddbf8e037225fe7cd07b234ceea5941f88a61aca999e4a6b8dbacc56"
SRC_URI[azbox-initrd-azboxme.md5sum] = "b2fd2b894c2890dd83a003723df0ba60"
SRC_URI[azbox-initrd-azboxme.sha256sum] = "5a9bcefd24df1785c7e403e1fba6e7d8cff89df70bc4aa03cb6684c6b470fd9c"
SRC_URI[azbox-initrd-azboxminime.md5sum] = "5b7639dd8a18aa7e3a99bbd284dfdfc6"
SRC_URI[azbox-initrd-azboxminime.sha256sum] = "3acca014a1c4153227542d6784b4948baaaa7d7ee58f52747186b3068023aad4"

S = "${WORKDIR}/linux-${KV}"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "zbimage-linux-xload"
KERNEL_IMAGETYPE = "zbimage-linux-xload"
KERNEL_IMAGEDEST = "/tmp"


FILES_kernel-image = "/boot/zbimage-linux-xload"

CFLAGS_prepend = "-I${WORKDIR} "

do_configure_prepend() {
	oe_machinstall -m 0644 ${WORKDIR}/${MACHINE}_defconfig ${S}/.config
	oe_runmake oldconfig
}

kernel_do_compile() {
	gcc ${CFLAGS} ${WORKDIR}/genzbf.c -o ${WORKDIR}/genzbf
	
	install -m 0755 ${WORKDIR}/genzbf ${S}/arch/mips/boot/

	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
	oe_runmake include/linux/version.h CC="${KERNEL_CC}" LD="${KERNEL_LD}"
	oe_runmake ${KERNEL_IMAGETYPE} CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${AR}" OBJDUMP="${OBJDUMP}" NM="${NM}"
	oe_runmake modules CC="${KERNEL_CC}" LD="${KERNEL_LD}" AR="${AR}" OBJDUMP="${OBJDUMP}"
}

do_install_append () {
	install -d ${D}/boot
	install -m 0644 ${S}/arch/mips/boot/zbimage-linux-xload ${D}/boot/zbimage-linux-xload

}
