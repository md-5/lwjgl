/* 
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are 
 * met:
 * 
 * * Redistributions of source code must retain the above copyright 
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of 
 *   its contributors may be used to endorse or promote products derived 
 *   from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
/**
 * $Id$
 *
 * Linux cursor handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <X11/X.h>
#include <X11/Xlib.h>
#include <X11/Xcursor/Xcursor.h>
#include "org_lwjgl_input_Cursor.h"
#include "org_lwjgl_opengl_LinuxDisplay.h"
#include "Window.h"
#include "common_tools.h"

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay_nCreateCursor
  (JNIEnv *env, jobject this, jobject handle_buffer, jint width, jint height, jint x_hotspot, jint y_hotspot, jint num_images, jobject image_buffer, jint images_offset, jobject delay_buffer, jint delays_offset)
{
	if ((*env)->GetDirectBufferCapacity(env, handle_buffer) < sizeof(Cursor)) {
		throwException(env, "Handle buffer not large enough");
		return;
	}
	Display *disp = incDisplay(env);
	if (disp == NULL)
		return;
	const int *delays = NULL;
	if (delay_buffer != NULL)
		delays = (const int *)(*env)->GetDirectBufferAddress(env, delay_buffer) + delays_offset;		
	XcursorPixel *pixels = (XcursorPixel *)(*env)->GetDirectBufferAddress(env, image_buffer) + images_offset;
	int stride = width*height;
	XcursorImages *cursor_images = XcursorImagesCreate(num_images);
	if (cursor_images == NULL) {
		decDisplay();
		throwException(env, "Could not allocate cursor.");
		return;
	}
	cursor_images->nimage = num_images;
	int i;
	for (i = 0; i < num_images; i++) {
		XcursorImage *cursor_image = XcursorImageCreate(width, height);
		cursor_image->xhot = x_hotspot;
		cursor_image->yhot = y_hotspot;
		cursor_image->pixels = &(pixels[stride*i]);
		if (num_images > 1)
			cursor_image->delay = delays[i];		
		cursor_images->images[i] = cursor_image;
	}
	Cursor *cursor = (Cursor *)(*env)->GetDirectBufferAddress(env, handle_buffer);
	*cursor = XcursorImagesLoadCursor(disp, cursor_images);
	XcursorImagesDestroy(cursor_images);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_LinuxDisplay__destroyCursor
  (JNIEnv *env, jobject this, jobject cursor_handle_buffer)
{
	Cursor *cursor = (Cursor *)(*env)->GetDirectBufferAddress(env, cursor_handle_buffer);
//	Cursor cursor = (Cursor)cursor_handle;
	XFreeCursor(getDisplay(), *cursor);
	decDisplay();
}