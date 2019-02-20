package com.example.bee2.utility;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

@Component
public class ImageUtility {
	public byte[] resizeImage(byte[] srcByteArray) {
		byte[] dstByteArray = null;
		
		try {
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(srcByteArray));
			AffineTransformOp xform = new AffineTransformOp(AffineTransform.getScaleInstance((double)500, (double)500), AffineTransformOp.TYPE_BILINEAR);
			BufferedImage dst = new BufferedImage(500, 500, src.getType());
			xform.filter(src, dst);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(dst, "jpg", baos);
			dstByteArray = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dstByteArray;
	}
}
