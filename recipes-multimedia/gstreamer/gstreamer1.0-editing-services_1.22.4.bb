DESCRIPTION = "The 'GStreamer Editing Services' is a library to simplify the creation \
               of multimedia editing applications. Based on the GStreamer multimedia \
               framework and the GNonLin set of plugins, its goals are to suit all \
               types of editing-related applications."
HOMEPAGE = "https://gstreamer.freedesktop.org/"
BUGTRACKER = "https://gitlab.freedesktop.org/gstreamer/gst-editing-services/-/issues"

SRC_URI = "https://gstreamer.freedesktop.org/src/gst-editing-services/gst-editing-services-${PV}.tar.xz \
           file://0001-uri-asset-use-custom-context-for-running-discoverer.patch \
           file://0002-smart-adder-remove-ghost-pad.patch \
"
SRC_URI[sha256sum] = "453846cfa471a0c1c8014551bd484fbcf334f0e4430592317afbb23775e69bd2"

S = "${WORKDIR}/gst-editing-services-${PV}"

LICENSE = "LGPL-2.1-or-later & GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d"

DEPENDS += "gstreamer1.0-plugins-base gstreamer1.0-plugins-bad"

inherit meson pkgconfig

EXTRA_OEMESON += " \
  -Ddoc=disabled \
"
ARM_INSTRUCTION_SET:armv4 = "arm"
ARM_INSTRUCTION_SET:armv5 = "arm"

FILES:${PN} += " \
  ${datadir}/gstreamer-1.0/validate/scenarios/ges-edit-clip-while-paused.scenario \
  ${datadir}/bash-completion/completions/ges-launch-1.0 \
  ${libdir}/gstreamer-1.0/libgstnle.so \
  ${libdir}/gstreamer-1.0/libgstges.so \
  ${libdir}/gst-validate-launcher/python/launcher/apps/geslaunch.py \
  ${libdir}/python3.11/site-packages/gi/overrides/GES.py \
"
