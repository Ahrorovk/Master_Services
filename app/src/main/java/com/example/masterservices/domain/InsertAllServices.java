package com.example.masterservices.domain;

import com.example.masterservices.R;
import com.example.masterservices.data.model.local.ServiceEntity;
import com.example.masterservices.domain.repository.MasterRepository;

public class InsertAllServices {

    MasterRepository masterRepository;

    public InsertAllServices(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public void invoke() {
        masterRepository.insertService(
                new ServiceEntity(0,
                        "Стрижка мужская",
                        "Иван Иванов",
                        1000,
                        "Классическая мужская стрижка с использованием машинки и ножниц. Включает мытье головы и укладку.",
                        R.drawable.masterr,
                        "Парикмахер"
                )
        );
        masterRepository.insertService(
                new ServiceEntity(1,
                        "Маникюр классический",
                        "Анна Смирнова",
                        800,
                        "Классический маникюр с обработкой кутикулы, приданием формы ногтям и нанесением лака.",
                        R.drawable.manikur,
                        "Мастер маникюра"
                )
        );
        masterRepository.insertService(
                new ServiceEntity(2,
                        "Педикюр SPA",
                        "Марина Петрова",
                        1500,
                        "Глубокий педикюр с использованием скрабов и масок. Включает обработку стоп и ногтей.",
                        R.drawable.pedikur,
                        "Мастер педикюра"
                )
        );
        masterRepository.insertService(
                new ServiceEntity(3,
                        "Окрашивание волос",
                        "Ольга Сидорова",
                        3000,
                        "Профессиональное окрашивание волос с использованием качественных красок и уходовых средств.",
                        R.drawable.masterr,
                        "Колорист-парикмахер"
                )
        );
        masterRepository.insertService(
                new ServiceEntity(4,
                        "Маникюр аппаратный",
                        "Анна Смирнова",
                        800,
                        "Классический маникюр с обработкой кутикулы, приданием формы ногтям и нанесением лака.",
                        R.drawable.manikur,
                        "Мастер маникюра"
                )
        );
        masterRepository.insertService(
                new ServiceEntity(5,
                        "Маникюр Европейский",
                        "Анна Смирнова",
                        800,
                        "Классический маникюр с обработкой кутикулы, приданием формы ногтям и нанесением лака.",
                        R.drawable.manikur,
                        "Мастер маникюра"
                )
        );
        masterRepository.insertService(
                new ServiceEntity(6,
                        "SPA-Маникюр",
                        "Анна Смирнова",
                        800,
                        "Классический маникюр с обработкой кутикулы, приданием формы ногтям и нанесением лака.",
                        R.drawable.manikur,
                        "Мастер маникюра"
                )
        );

        masterRepository.insertService(
                new ServiceEntity(7,
                        "Укорачивание волос",
                        "Олег Алексеев",
                        2600,
                        "Профессиональное Укорачивание волос с использованием качественного оборудования и уходовых средств.",
                        R.drawable.masterr,
                        "Парикмахер"
                )
        );
    }

}
