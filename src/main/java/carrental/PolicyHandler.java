package carrental;

import carrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PolicyHandler{

    @Autowired
    PointRepository pointRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCarRented_PointInsert(@Payload CarRented carRented){

        if (carRented.isMe() && "CAR_RENTED".equals(carRented.getProcStatus())) {
            Point point = new Point();
            //point.setId(carRented.getId());   // 이력성으로 계속 적재함
            point.setCarNo(carRented.getCarNo());
            point.setPoint(Long.valueOf(10000));
            point.setResrvNo(carRented.getResrvNo());
            pointRepository.save(point);
            System.out.println("##### listener PointInsert >> plus [CAR_RENTED] : " + carRented.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCarRentalCanceled_PointInsert(@Payload CarRentalCanceled carRentalCanceled){

        if(carRentalCanceled.isMe() && "CAR_RENTAL_CANCELED".equals(carRentalCanceled.getProcStatus())){
            Point point = new Point();
            //point.setId(carRentalCanceled.getId());   // 이력성으로 계속 적재함
            point.setCarNo(carRentalCanceled.getCarNo());
            point.setPoint(Long.valueOf(-10000));
            point.setResrvNo(carRentalCanceled.getResrvNo());
            pointRepository.save(point);
            System.out.println("##### listener PointInsert >> minus [carRentalCanceled] : " + carRentalCanceled.toJson());
        }
    }

}
