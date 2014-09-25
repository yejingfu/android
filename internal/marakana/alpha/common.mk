# Since this file can also be referenced by alpha-sdk_addon
# we cannot assume LOCAL_PATH points to the directory where
# this file is located. Instead, we create another variable
# to capture this directory.
MY_PATH := $(LOCAL_PATH)/../alpha

# Include all makefiles in sub-directories (one level deep)
include $(call all-subdir-makefiles)

# These are the hardware-specific features
PRODUCT_COPY_FILES += \
frameworks/native/data/etc/handheld_core_hardware.xml:system/etc/permissions/handheld_core_hardware.xml
        
# Enable overlays
DEVICE_PACKAGE_OVERLAYS := $(MY_PATH)/overlay

# Include all packages from this file
include $(MY_PATH)/packages.mk

LOCAL_KERNEL := $(MY_PATH)/kernel
PRODUCT_COPY_FILES += $(LOCAL_KERNEL):kernel
PRODUCT_COPY_FILES += $(MY_PATH)/init.goldfish.rc:root/init.goldfish.rc
PRODUCT_COPY_FILES += $(MY_PATH)/ueventd.goldfish.rc:root/ueventd.goldfish.rc
PRODUCT_COPY_FILES += $(MY_PATH)/fstab.goldfish:root/fstab.goldfish
