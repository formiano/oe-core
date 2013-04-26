inherit image_types

IMAGE_CMD_azboxcramfs = "mkfs.cramfs ${IMAGE_ROOTFS} ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.cramfs;"

IMAGE_DEPENDS_azboxcramfs = "cramfs-native"

IMAGE_TYPES += "cramfs"
