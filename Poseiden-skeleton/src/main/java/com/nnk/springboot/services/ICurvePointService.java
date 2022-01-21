package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.CurvePoint;

public interface ICurvePointService {

	public List<CurvePoint> findAll();

	public @Valid CurvePoint save(@Valid CurvePoint curvePoint);

	public CurvePoint findById(Integer id);

	public CurvePoint deleteById(Integer id);

}
