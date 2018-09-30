package com.boot.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipWreckRepository;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {

	@Autowired
	private ShipWreckRepository shipWreckRepository;
	
	@RequestMapping(value="shipwrecks", method=RequestMethod.GET)
	 public List<Shipwreck> list(){
		return shipWreckRepository.findAll();
	 }
	
	@RequestMapping(value="shipwrecks", method=RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		
		return shipWreckRepository.saveAndFlush(shipwreck);
	}
	
	@RequestMapping(value="shipwreck/{id}", method=RequestMethod.PUT)
	public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
		Shipwreck existingShipwreck = shipWreckRepository.findOne(id);
		BeanUtils.copyProperties(shipwreck, existingShipwreck);
		return shipWreckRepository.saveAndFlush(existingShipwreck);
		
	}
	
	@RequestMapping(value="shipwreck/{id}", method=RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable Long id) {
		Shipwreck existingShipwreck = shipWreckRepository.findOne(id);
		shipWreckRepository.delete(existingShipwreck);
		return existingShipwreck;
	}
}
