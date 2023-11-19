package org.input.inputevent;

import java.util.HashMap;
import java.util.Map;

public enum KeyboardEvent implements InputEvent{
    KEYBOARD_0_HOLD(48),
    KEYBOARD_1_HOLD(49),
    KEYBOARD_2_HOLD(50),
    KEYBOARD_3_HOLD(51),
    KEYBOARD_4_HOLD(52),
    KEYBOARD_5_HOLD(53),
    KEYBOARD_6_HOLD(54),
    KEYBOARD_7_HOLD(55),
    KEYBOARD_8_HOLD(56),
    KEYBOARD_9_HOLD(57),
    KEYBOARD_SEMICOLON_HOLD(59), // ';'
    KEYBOARD_EQUAL_HOLD(61),     // '='
    KEYBOARD_A_HOLD(65),
    KEYBOARD_B_HOLD(66),
    KEYBOARD_C_HOLD(67),
    KEYBOARD_D_HOLD(68),
    KEYBOARD_E_HOLD(69),
    KEYBOARD_F_HOLD(70),
    KEYBOARD_G_HOLD(71),
    KEYBOARD_H_HOLD(72),
    KEYBOARD_I_HOLD(73),
    KEYBOARD_J_HOLD(74),
    KEYBOARD_K_HOLD(75),
    KEYBOARD_L_HOLD(76),
    KEYBOARD_M_HOLD(77),
    KEYBOARD_N_HOLD(78),
    KEYBOARD_O_HOLD(79),
    KEYBOARD_P_HOLD(80),
    KEYBOARD_Q_HOLD(81),
    KEYBOARD_R_HOLD(82),
    KEYBOARD_S_HOLD(83),
    KEYBOARD_T_HOLD(84),
    KEYBOARD_U_HOLD(85),
    KEYBOARD_V_HOLD(86),
    KEYBOARD_W_HOLD(87),
    KEYBOARD_X_HOLD(88),
    KEYBOARD_Y_HOLD(89);

    private final int keyCode;
    private static final Map<Integer, KeyboardEvent> keyCodeMap = new HashMap<>();

    static {
        for (KeyboardEvent keyEvent : values()) {
            keyCodeMap.put(keyEvent.toKeyCode(), keyEvent);
        }
    }

    public static KeyboardEvent fromKeyCode(int keyCode) {
        return keyCodeMap.get(keyCode);
    }


    KeyboardEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public int toKeyCode() {
        return keyCode;
    }


}
