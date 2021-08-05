package Mole.Game;

import java.util.LinkedList;

import Mole.Game.Entities.EntityA;
import Mole.Game.Entities.EntityB;

public class Physics { // 충돌감지, 사각형의 범위안에 있으면 true로 반환 없으면 false

	public static boolean Collision(EntityA entA, LinkedList<Mole> entB) { // 인간 대 두더지 N , 총알1개와 두더지 1하나의 관계
		for (int i = 0; i < entB.size(); i++)
			if (entA.getBounds().intersects(entB.get(i).getBounds())) {
				entB.remove(i);
				return true;
			}
		return false;
	}

	public static boolean Collision(EntityA entA, EntityB entB) { // 인간 대 두더지
		if (entA.getBounds().intersects(entB.getBounds())) {
			return true;
		}
		return false;
	}
}
