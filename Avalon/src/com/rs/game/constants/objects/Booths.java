package com.rs.game.constants.objects;



/**
 * @author Anaxarchus
 * This enum class is being created to allow for better readability.
 */

public enum Booths {


    BANK_BOOTH(782, "Bank booth"),
    BANK_BOOTH_1(2213, "Bank booth"),
    CLOSED_BANK_BOOTH(2215, "Closed bank booth"),
    BANK_BOOTH_2(3045, "Bank booth"),
    TOLL_BOOTH(4453, "Toll booth"),
    BANK_BOOTH_3(5276, "Bank booth"),
    CLOSED_BANK_BOOTH_1(5277, "Closed bank booth"),
    CLOSED_BANK_BOOTH_2(6083, "Closed bank booth"),
    BANK_BOOTH_4(6084, "Bank booth"),
    BANK_BOOTH_5(10517, "Bank booth"),
    CLOSED_BANK_BOOTH_3(10518, "Closed bank booth"),
    BANK_BOOTH_6(11338, "Bank booth"),
    BANK_BOOTH_7(11758, "Bank booth"),
    BANK_BOOTH_8(12798, "Bank Booth"),
    BANK_BOOTH_9(12799, "Bank Booth"),
    BANK_BOOTH_10(12800, "Bank Booth"),
    BANK_BOOTH_11(12801, "Bank Booth"),
    BANK_BOOTH_12(14369, "Bank booth"),
    BANK_BOOTH_13(14370, "Bank booth"),
    BANK_BOOTH_14(16700, "Bank booth"),
    EXPERIMENT_BOOTH(17899, "Experiment booth"),
    BANK_BOOTH_15(19230, "Bank booth"),
    PRIVATE_BANK_BOOTH(20323, "Private bank booth"),
    PRIVATE_BANK_BOOTH_1(20324, "Private bank booth"),
    BANK_BOOTH_16(20325, "Bank booth"),
    BANK_BOOTH_17(20326, "Bank booth"),
    BANK_BOOTH_18(20327, "Bank booth"),
    BANK_BOOTH_19(20328, "Bank booth"),
    BANK_BOOTH_20(22819, "Bank booth"),
    INFORMATION_BOOTH(24452, "Information booth"),
    BANK_BOOTH_21(24914, "Bank booth"),
    BANK_BOOTH_22(25808, "Bank booth"),
    CLOSED_BOOTH(25809, "Closed booth"),
    BANK_BOOTH_23(26972, "Bank booth"),
    BANK_BOOTH_24(29085, "Bank booth"),
    BANK_BOOTH_25(34205, "Bank booth"),
    CLOSED_BANK_BOOTH_4(34206, "Closed bank booth"),
    CLOSED_BANK_BOOTH_5(34207, "Closed bank booth"),
    BANK_BOOTH_26(34752, "Bank booth"),
    BANK_BOOTH_27(35647, "Bank booth"),
    BANK_BOOTH_28(35648, "Bank booth"),
    BANK_BOOTH_29(36262, "Bank booth"),
    TOLL_BOOTH_1(36471, "Toll booth"),
    TOLL_BOOTH_2(36472, "Toll booth"),
    BANK_BOOTH_30(36786, "Bank booth"),
    CLOSED_BANK_BOOTH_6(36787, "Closed Bank booth"),
    BANK_BOOTH_31(37474, "Bank booth"),
    PHOTO_BOOTH(46396, "Photo booth"),
    BANK_BOOTH_32(49018, "Bank booth"),
    BANK_BOOTH_33(49019, "Bank booth"),
    BANK_BOOTH_34(52397, "Bank booth"),
    BANK_BOOTH_35(52589, "Bank booth"),
    PHOTO_BOOTH_1(53128, "Photo booth"),
    PHOTO_BOOTH_2(53130, "Photo booth"),
    PHOTO_BOOTH_3(53132, "Photo booth"),
    PHOTO_BOOTH_4(53700, "Photo booth"),
    PHOTO_BOOTH_5(55607, "Photo booth"),
    CHANGING_BOOTH(62145, "Changing booth"),
    BANK_BOOTH_36(69021, "Bank booth"),
    BANK_BOOTH_37(69022, "Bank booth"),
    BANK_BOOTH_38(69023, "Bank booth"),
    BANK_BOOTH_39(69024, "Bank booth");



    private final int id;
    private final String description;

    Booths(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
