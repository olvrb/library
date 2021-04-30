package com.oliver.library.Application.Components;

import com.oliver.library.Application.Repositories.RentalObjectRepository;
import com.oliver.library.Application.Repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CSVLoaderComponent {
    @Autowired
    private RentalObjectRepository rentalObjectRepository;

    @Autowired
    private RentalRepository rentalRepository;
}
