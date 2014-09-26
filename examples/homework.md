Android Internals Shanghai - 22 September 2014

Marius Mailat - Appsrise.com, marius.mailat@gmail.com


Homework Day 1

- compile the platform using > time make and follow the instruction on page 212-218
- modify the name of the Browser app from Browser to "{yourname} Browser" for the chinese locales

Homework Day 2

- use the instructions on page 220 in the course to have the library and the binary created

Homework Day 3

- use the instructions on page 219 for having a custom kernel
- use the instructions on page 221 for adding a custom daemon and have it in the init.goldfish.rc added

For Kernel use the bellow instructions:

Step 1

- cp device/generic/goldfish/init.goldfish.rc device/marakana/alpha/init.goldfish.rc
- cp device/generic/goldfish/ueventd.goldfish.rc device/marakana/alpha/goldfish.ueventd.goldfish.rc
- cp device/generic/goldfish/fstab.goldfish device/marakana/alpha/fstab.goldfish

Step 2

- cp prebuilts/qemu-kernel/x86/kernel-qemu device/marakana/alpha/kernel

Step 3

- in device/marakana/alpha/BoardConfig.mk
- change to TARGET_NO_KERNEL := false

Step 4
- in  device/marakana/alpha/common.mk change to 

- LOCAL_KERNEL := $(MY_PATH)/kernel
- PRODUCT_COPY_FILES += $(LOCAL_KERNEL):kernel

- PRODUCT_COPY_FILES += $(MY_PATH)/init.goldfish.rc:root/init.goldfish.rc
- PRODUCT_COPY_FILES += $(MY_PATH)/ueventd.goldfish.rc:root/ueventd.goldfish.rc
- PRODUCT_COPY_FILES += $(MY_PATH)/fstab.goldfish:root/fstab.goldfish

Step 5 - Work further on ShanghaiWeather with a ListActivity
- use the url: http://api.openweathermap.org/data/2.5/forecast?q=Shanghai,en&units=metric
- create 2 button on the MainActivity and redirect to ForecastActivity.java
- ForecastActivity.java will extend a ListActivity and please use the example project "06_ListView", class SimpleListActivity.java

Homework Day 4

- expose the native library via JNI, please follow the steps on the page 222
- create a button in the WeatherActivity calling the RefreshService.java We will pass as reference a Messenger for getting the weather response. We use the template at page: 101 in the course! 

Homework Day 5

- implement the changes on the chapter "Consuming our a Custom Java/JNI→Native Library via a Custom App" at the page 223 in the course
- modify the Weather app and add a Widget to it