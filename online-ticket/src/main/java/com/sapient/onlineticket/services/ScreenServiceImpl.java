package com.sapient.onlineticket.services;

import com.sapient.onlineticket.Util.OnlineBookingException;
import com.sapient.onlineticket.entity.ScreenPrimaryKey;
import com.sapient.onlineticket.entity.Screens;
import com.sapient.onlineticket.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceImpl {

    @Autowired
    private ScreenRepository screenRepository;

    public Screens registerScreens(Screens screen) {

        return screenRepository.save(screen);
    }

    public List<Screens> getAllScreens() {
        return screenRepository.findAll();
    }

    public String deleteScreens(String theaterId, String movieId, String screenStartsAt, Date date) {
        ScreenPrimaryKey screenPrimaryKey = ScreenPrimaryKey.builder()
                .movieId(movieId)
                .theaterId(theaterId)
                .screenStartsAt(screenStartsAt)
                .screenDate(date)
                .build();
        Optional<Screens> screenPk = screenRepository.findById(screenPrimaryKey);
        if(!screenPk.isPresent())
        {
            throw new OnlineBookingException("Show is not currently present");
        }

        screenRepository.delete(screenPk.get());
        return "Show deleted";
    }
}
