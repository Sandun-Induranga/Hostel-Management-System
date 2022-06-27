package lk.d24.hostel_system.bo;

import lk.d24.hostel_system.bo.custom.impl.ReserveRoomBOImpl;
import lk.d24.hostel_system.bo.custom.impl.RoomBOImpl;
import lk.d24.hostel_system.bo.custom.impl.StudentBOImpl;
import lk.d24.hostel_system.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM, USER, RESERVATION
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case USER:
                return new UserBOImpl();
            case RESERVATION:
                return new ReserveRoomBOImpl();
            default:
                return null;
        }
    }
}
