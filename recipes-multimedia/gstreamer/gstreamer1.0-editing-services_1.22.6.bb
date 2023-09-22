DESCRIPTION = "The 'GStreamer Editing Services' is a library to simplify the creation \
               of multimedia editing applications. Based on the GStreamer multimedia \
               framework and the GNonLin set of plugins, its goals are to suit all \
               types of editing-related applications."
HOMEPAGE = "https://gstreamer.freedesktop.org/"
BUGTRACKER = "https://gitlab.freedesktop.org/gstreamer/gst-editing-services/-/issues"

SRC_URI = "https://gstreamer.freedesktop.org/src/gst-editing-services/gst-editing-services-${PV}.tar.xz \
           file://0001-uri-asset-use-custom-context-for-running-discoverer.patch \
           file://0002-smart-adder-remove-ghost-pad.patch \
           file://0003-nlecomposition-disable-dot-output.patch \
           file://0004-ges-uri-asset-disable-cached-discoverers.patch \
           file://0005-uri-asset-30s-timeout-for-sync-request.patch \
           file://0006-ges-asset-and-timeline-cleanup-functions.patch \
"
SRC_URI[sha256sum] = "748d423672c597f876e130804fb984848f5b4b89efd78a506cb17f7646795301"

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
