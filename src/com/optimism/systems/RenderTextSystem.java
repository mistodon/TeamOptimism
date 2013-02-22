package com.optimism.systems;

import java.awt.Graphics2D;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Projector;
import com.optimism.components.Position;
import com.optimism.components.Text;
import com.optimism.tools.Tuple2Int;


public class RenderTextSystem extends EntityProcessingSystem {
	
	private Graphics2D graphics;
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Text> tm;
	
	
	@SuppressWarnings("unchecked")
	public RenderTextSystem(Graphics2D graphics) {
		super(Aspect.getAspectForAll(Position.class, Text.class));
		this.graphics = graphics;
	}
	
	@Override
	public void process(Entity entity) {
		Graphics2D g = (Graphics2D) graphics.create();	// Make a graphics copy (apparently this is good practice??)

		Position pos = pm.get(entity);
		Text str = tm.get(entity);
		Tuple2Int screenPos = Projector.worldToScreen(pos);
		
		g.drawString(str.getString(), screenPos.getX(), screenPos.getY());
		
		g.dispose();	// Dispose of the copy.
	}
	
}
