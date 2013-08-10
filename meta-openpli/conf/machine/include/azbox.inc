MACHINE_ESSENTIAL_EXTRA_RDEPENDS += " \
	azbox-dvb-modules \	
	"

DVBPROVIDER = "kernel"

MACHINE_EXTRA_RRECOMMENDS += " \
	gst-plugin-dvbmediasink \			
	azbox-mrua \
	"

EXTRA_IMAGEDEPENDS += " \
	module-init-tools-cross \
	module-init-tools \	
	"

DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm"

MACHINE_FEATURES += "alsa usbhost wifi nl80211 3dtv switchoff lpcm textlcd 32bpp hdtv dvbapi5 dvb-c blindscan-dvbs"

MACHINE_KERNEL_PR = "${AZVERSION}.${AZREVISION}"

PREFERRED_VERSION_linux-azbox = "3.9.2"
PREFERRED_VERSION_linux-libc-headers = "3.3"

PREFERRED_PROVIDER_virtual/kernel = "linux-azbox"
PREFERRED_PROVIDER_virtual/blindscan-dvbs = "azbox-blindscan-dvbs-utils"

require conf/machine/include/tune-mips32.inc
include conf/machine/include/autoload-filesystems.inc

SYSVINIT_ENABLED_GETTYS = ""
