inherit image_types

IMAGE_CMD_azboxhdcramfs = "mkcramfs ${IMAGE_ROOTFS} ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.cramfs;"

IMAGE_DEPENDS_azboxhdcramfs = "cramfs-native"

IMAGE_TYPES += "cramfs"
