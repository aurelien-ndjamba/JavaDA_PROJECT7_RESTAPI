package com.nnk.springboot.services;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	private CurvePointRepository curvePointRepository;

	public ArrayList<CurvePoint> findAll() {
		return curvePointRepository.findAll();
	}

	public @Valid CurvePoint save(@Valid CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	public CurvePoint findById(Integer id) {
		return curvePointRepository.findById(id).get();
	}

	public void deleteById(Integer id) {
		curvePointRepository.deleteById(id);
	}

}
