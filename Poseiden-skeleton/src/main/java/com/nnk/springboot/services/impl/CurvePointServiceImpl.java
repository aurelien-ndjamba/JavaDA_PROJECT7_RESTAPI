package com.nnk.springboot.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.ICurvePointService;

/**
 * Service CurvePointService
 */
@Service
public class CurvePointServiceImpl implements ICurvePointService {

	@Autowired
	private CurvePointRepository curvePointRepository;

	public void setCurvePointRepository(CurvePointRepository curvePointRepository) {
		this.curvePointRepository = curvePointRepository;
	}

	@Override
	public List<CurvePoint> findAll() {
		return curvePointRepository.findAll();
	}

	@Override
	public @Valid CurvePoint save(@Valid CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	@Override
	public CurvePoint findById(Integer id) {
		return curvePointRepository.findById(id).get();
	}

	@Override
	public CurvePoint deleteById(Integer id) {
		CurvePoint result = curvePointRepository.findById(id).get();
		curvePointRepository.deleteById(id);
		return result;
	}

}
