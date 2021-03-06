package com.optimism.systems;

import java.awt.Color;
import java.awt.Graphics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.GameData;
import com.optimism.Projector;
import com.optimism.components.OrbitRing;
import com.optimism.components.Position;
import com.optimism.tools.Tuple2Int;


public class OrbitRenderSystem extends EntityProcessingSystem {
	
	private Graphics graphics;
	private GameData data;
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<OrbitRing> om;
	
	@SuppressWarnings("unchecked")
	public OrbitRenderSystem(Graphics graphics, GameData data) {
		super(Aspect.getAspectForAll(Position.class, OrbitRing.class));
		this.graphics = graphics;
		this.data = data;
	}
	
	public void process(Entity entity) {
		Position pos = pm.get(entity);
		double radius = om.get(entity).radius;
		Tuple2Int spos = Projector.worldToScreen(pos);
		if (data.hurt > 0) {
			graphics.setColor(Color.red);
			data.hurt -= 1;
		} else {
			graphics.setColor(Color.white);
		}
		graphics.drawOval((int) (spos.getX()-radius), (int) (spos.getY()-radius), (int) radius*2, (int) radius*2);
	}

}
