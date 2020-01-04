package ru.func.machinereference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.func.machinereference.entity.Car;
import ru.func.machinereference.repository.CarRepository;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/createCar", method = POST)
    public String createCar(@RequestParam String name,
                            @RequestParam String company) {
        carRepository.save(Car.builder()
                .display(name)
                .company(company)
                .build()
        );

        return "createCar";
    }

    @RequestMapping(path = "/findCar", method = POST)
    public ModelAndView findCar(@RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("findCar");
        modelAndView.addObject("id", id);

        carRepository.findById(id)
                .ifPresent(car -> modelAndView.addObject("car", car));

        return modelAndView;
    }

    @RequestMapping(path = "/findCarsByCompany", method = POST)
    public ModelAndView findCarsByCompany(@RequestParam String company) {
        ModelAndView modelAndView = new ModelAndView("findCarsByCompany");
        modelAndView.addObject("company", company);

        modelAndView.addObject("cars", carRepository.findByCompany(company));

        return modelAndView;
    }
}
