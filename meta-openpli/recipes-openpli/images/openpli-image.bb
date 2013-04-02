require conf/license/openpli-gplv2.inc

inherit task image

IMAGE_INSTALL = " \
	${ROOTFS_PKGMANAGE} \
	3rd-party-feed-configs \
	avahi-daemon \
	cifs \
	distro-feed-configs \
	dropbear \
	e2fsprogs-e2fsck \
	e2fsprogs-mke2fs \
	e2fsprogs-tune2fs \
	early-configure \
	fakelocale \
	nfs-utils-client \
	openpli-bootlogo \
	opkg \
	sambaserver \
	sdparm \
	task-base \
	task-core-boot \
	tuxbox-common \
	tuxbox-links \
	tzdata \
	util-linux-sfdisk \
	volatile-media \
	vsftpd \
	dvbsnoop \
	hddtemp \
	ntp \
	parted \
	util-linux-blkid \
	pe-version-info \
	"

OPTIONAL_PACKAGES ?= ""
OPTIONAL_PACKAGES += " \
	autofs \
	autossh \
	ctorrent \
	cups \
	djmount \
	dvdfs \
	gdb \
	hdparm \
	inadyn-mt \
	iperf \
	joe \
	mc \
	minidlna \
	mpd \
	mtd-utils \
	nano \
	ntfs-3g \
	openresolv \
	openssh \
	openvpn \
	procps \
	pyload \
	rsync \
	rtorrent \
	sabnzbd \
	samba \
	sshpass \
	smartmontools \
	strace \
	tcpdump \
	tmux \
	transmission \
	vim \
	wakelan \
	xfsprogs \
	zeroconf \
	"

export IMAGE_BASENAME = "openazbox"
IMAGE_LINGUAS = ""

IMAGE_FEATURES += "package-management"
