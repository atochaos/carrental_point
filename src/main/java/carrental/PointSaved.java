package carrental;

public class PointSaved extends AbstractEvent {

    private Long id;
    private String carNo;
    private Long point;
    private String resrvNo;

    public PointSaved(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }
    public String getResrvNo() {
        return resrvNo;
    }

    public void setResrvNo(String resrvNo) {
        this.resrvNo = resrvNo;
    }
}
