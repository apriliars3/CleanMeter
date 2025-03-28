package app.cleanmeter.core.common.mahm

import kotlinx.serialization.Serializable

@Serializable
enum class SourceID(val value: Int) {
    MONITORING_SOURCE_ID_UNKNOWN(-1),
    MONITORING_SOURCE_ID_GPU_TEMPERATURE(0x00000000),
    MONITORING_SOURCE_ID_PCB_TEMPERATURE(0x00000001),
    MONITORING_SOURCE_ID_MEM_TEMPERATURE(0x00000002),
    MONITORING_SOURCE_ID_VRM_TEMPERATURE(0x00000003),
    MONITORING_SOURCE_ID_FAN_SPEED(0x00000010),
    MONITORING_SOURCE_ID_FAN_TACHOMETER(0x00000011),
    MONITORING_SOURCE_ID_FAN_SPEED2(0x00000012),
    MONITORING_SOURCE_ID_FAN_TACHOMETER2(0x00000013),
    MONITORING_SOURCE_ID_FAN_SPEED3(0x00000014),
    MONITORING_SOURCE_ID_FAN_TACHOMETER3(0x00000015),
    MONITORING_SOURCE_ID_CORE_CLOCK(0x00000020),
    MONITORING_SOURCE_ID_SHADER_CLOCK(0x00000021),
    MONITORING_SOURCE_ID_MEMORY_CLOCK(0x00000022),
    MONITORING_SOURCE_ID_GPU_USAGE(0x00000030),
    MONITORING_SOURCE_ID_MEMORY_USAGE(0x00000031),
    MONITORING_SOURCE_ID_FB_USAGE(0x00000032),
    MONITORING_SOURCE_ID_VID_USAGE(0x00000033),
    MONITORING_SOURCE_ID_BUS_USAGE(0x00000034),
    MONITORING_SOURCE_ID_GPU_VOLTAGE(0x00000040),
    MONITORING_SOURCE_ID_AUX_VOLTAGE(0x00000041),
    MONITORING_SOURCE_ID_MEMORY_VOLTAGE(0x00000042),
    MONITORING_SOURCE_ID_AUX2_VOLTAGE(0x00000043),
    MONITORING_SOURCE_ID_FRAMERATE(0x00000050),
    MONITORING_SOURCE_ID_FRAMETIME(0x00000051),
    MONITORING_SOURCE_ID_FRAMERATE_MIN(0x00000052),
    MONITORING_SOURCE_ID_FRAMERATE_AVG(0x00000053),
    MONITORING_SOURCE_ID_FRAMERATE_MAX(0x00000054),
    MONITORING_SOURCE_ID_FRAMERATE_1DOT0_PERCENT_LOW(0x00000055),
    MONITORING_SOURCE_ID_FRAMERATE_0DOT1_PERCENT_LOW(0x00000056),
    MONITORING_SOURCE_ID_GPU_POWER(0x00000060),
    MONITORING_SOURCE_ID_GPU_TEMP_LIMIT(0x00000070),
    MONITORING_SOURCE_ID_GPU_POWER_LIMIT(0x00000071),
    MONITORING_SOURCE_ID_GPU_VOLTAGE_LIMIT(0x00000072),
    MONITORING_SOURCE_ID_GPU_UTIL_LIMIT(0x00000074),
    MONITORING_SOURCE_ID_GPU_SLI_SYNC_LIMIT(0x00000075),
    MONITORING_SOURCE_ID_CPU_TEMPERATURE(0x00000080),
    MONITORING_SOURCE_ID_CPU_USAGE(0x00000090),
    MONITORING_SOURCE_ID_RAM_USAGE(0x00000091),
    MONITORING_SOURCE_ID_PAGEFILE_USAGE(0x00000092),
    MONITORING_SOURCE_ID_CPU_CLOCK(0x000000A0),
    MONITORING_SOURCE_ID_GPU_TEMPERATURE2(0x000000B0),
    MONITORING_SOURCE_ID_PCB_TEMPERATURE2(0x000000B1),
    MONITORING_SOURCE_ID_MEM_TEMPERATURE2(0x000000B2),
    MONITORING_SOURCE_ID_VRM_TEMPERATURE2(0x000000B3),
    MONITORING_SOURCE_ID_GPU_TEMPERATURE3(0x000000C0),
    MONITORING_SOURCE_ID_PCB_TEMPERATURE3(0x000000C1),
    MONITORING_SOURCE_ID_MEM_TEMPERATURE3(0x000000C2),
    MONITORING_SOURCE_ID_VRM_TEMPERATURE3(0x000000C3),
    MONITORING_SOURCE_ID_GPU_TEMPERATURE4(0x000000D0),
    MONITORING_SOURCE_ID_PCB_TEMPERATURE4(0x000000D1),
    MONITORING_SOURCE_ID_MEM_TEMPERATURE4(0x000000D2),
    MONITORING_SOURCE_ID_VRM_TEMPERATURE4(0x000000D3),
    MONITORING_SOURCE_ID_GPU_TEMPERATURE5(0x000000E0),
    MONITORING_SOURCE_ID_PCB_TEMPERATURE5(0x000000E1),
    MONITORING_SOURCE_ID_MEM_TEMPERATURE5(0x000000E2),
    MONITORING_SOURCE_ID_VRM_TEMPERATURE5(0x000000E3),
    MONITORING_SOURCE_ID_PLUGIN_GPU(0x000000F0),
    MONITORING_SOURCE_ID_PLUGIN_CPU(0x000000F1),
    MONITORING_SOURCE_ID_PLUGIN_MOBO(0x000000F2),
    MONITORING_SOURCE_ID_PLUGIN_RAM(0x000000F3),
    MONITORING_SOURCE_ID_PLUGIN_HDD(0x000000F4),
    MONITORING_SOURCE_ID_PLUGIN_NET(0x000000F5),
    MONITORING_SOURCE_ID_PLUGIN_PSU(0x000000F6),
    MONITORING_SOURCE_ID_PLUGIN_UPS(0x000000F7),
    MONITORING_SOURCE_ID_PLUGIN_MISC(0x000000FF),
    MONITORING_SOURCE_ID_CPU_POWER(0x00000100);

    companion object {
        fun fromInt(value: Int) = values().firstOrNull { it.value == value } ?: MONITORING_SOURCE_ID_UNKNOWN

        fun fromString(value: String) = values().firstOrNull { it.name == value } ?: MONITORING_SOURCE_ID_UNKNOWN
    }
}
