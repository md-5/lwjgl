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
package org.lwjgl.devil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferChecks;
import org.lwjgl.LWJGLException;

/**
 * $Id$
 *
 * The core DevIL API.
 * 
 * @author captainjester <captainjester@users.sourceforge.net>
 * @version $Revision$
 */
public class IL {
    
    /** Have we been created? */
    protected static boolean created;
  
    public static final int IL_FALSE = 0; 
    public static final int IL_TRUE = 1; 
     
    // Matches OpenGL's right now. 
    public static final int IL_COLOUR_INDEX = 0x1900; 
    public static final int IL_COLOR_INDEX = 0x1900; 
    public static final int IL_RGB = 0x1907; 
    public static final int IL_RGBA = 0x1908; 
    public static final int IL_BGR = 0x80E0; 
    public static final int IL_BGRA = 0x80E1; 
    public static final int IL_LUMINANCE = 0x1909; 
     
    public static final int IL_BYTE = 0x1400; 
    public static final int IL_UNSIGNED_BYTE = 0x1401; 
    public static final int IL_SHORT = 0x1402; 
    public static final int IL_UNSIGNED_SHORT = 0x1403; 
    public static final int IL_INT = 0x1404; 
    public static final int IL_UNSIGNED_INT = 0x1405; 
    public static final int IL_FLOAT = 0x1406; 
    public static final int IL_DOUBLE = 0x140A; 
     
    public static final int IL_VENDOR = 0x1F00; 
     
    // IL-specific public const's 
    public static final int IL_VERSION_1_6_0 = 1; 
    public static final int IL_VERSION = 160; 
    public static final int IL_LOAD_EXT = 0x1F01; 
    public static final int IL_SAVE_EXT = 0x1F02; 
     
    // Attribute Bits 
    public static final int IL_ORIGIN_BIT = 0x1; 
    public static final int IL_FILE_BIT = 0x2; 
    public static final int IL_PAL_BIT = 0x4; 
    public static final int IL_FORMAT_BIT = 0x8; 
    public static final int IL_TYPE_BIT = 0x10; 
    public static final int IL_COMPRESS_BIT = 0x20; 
    public static final int IL_LOADFAIL_BIT = 0x40; 
    public static final int IL_FORMAT_SPECIFIC_BIT = 0x80; 
    public static final int IL_ALL_ATTRIB_BITS = 0xFFFFF; 
     
    // Palette types 
    public static final int IL_PAL_NONE = 0x400; 
    public static final int IL_PAL_RGB24 = 0x401; 
    public static final int IL_PAL_RGB32 = 0x402; 
    public static final int IL_PAL_RGBA32 = 0x403; 
    public static final int IL_PAL_BGR24 = 0x404; 
    public static final int IL_PAL_BGR32 = 0x405; 
    public static final int IL_PAL_BGRA32 = 0x406; 
     
    // Image types 
    public static final int IL_TYPE_UNKNOWN = 0x0; 
    public static final int IL_BMP = 0x420; 
    public static final int IL_CUT = 0x421; 
    public static final int IL_DOOM = 0x422; 
    public static final int IL_DOOM_FLAT = 0x423; 
    public static final int IL_ICO = 0x424; 
    public static final int IL_JPG = 0x425; 
    public static final int IL_LBM = 0x426; 
    public static final int IL_PCD = 0x427; 
    public static final int IL_PCX = 0x428; 
    public static final int IL_PIC = 0x429; 
    public static final int IL_PNG = 0x42A; 
    public static final int IL_PNM = 0x42B; 
    public static final int IL_SGI = 0x42C; 
    public static final int IL_TGA = 0x42D; 
    public static final int IL_TIF = 0x42E; 
    public static final int IL_CHEAD = 0x42F; 
    public static final int IL_RAW = 0x430; 
    public static final int IL_MDL = 0x431; 
    public static final int IL_WAL = 0x432; 
    public static final int IL_OIL = 0x433; 
    public static final int IL_LIF = 0x434; 
    public static final int IL_MNG = 0x435; 
    public static final int IL_JNG = 0x435; 
    public static final int IL_GIF = 0x436; 
    public static final int IL_DDS = 0x437; 
    public static final int IL_DCX = 0x438; 
    public static final int IL_PSD = 0x439; 
    public static final int IL_EXIF = 0x43A; 
    public static final int IL_PSP = 0x43B; 
    public static final int IL_PIX = 0x43C; 
    public static final int IL_PXR = 0x43D; 
    public static final int IL_XPM = 0x43E; 
     
    public static final int IL_JASC_PAL = 0x475; 
     
    // Error Types 
    public static final int IL_NO_ERROR = 0x0; 
    public static final int IL_INVALID_ENUM = 0x501; 
    public static final int IL_OUT_OF_MEMORY = 0x502; 
    public static final int IL_FORMAT_NOT_SUPPORTED = 0x503; 
    public static final int IL_INTERNAL_ERROR = 0x504; 
    public static final int IL_INVALID_VALUE = 0x505; 
    public static final int IL_ILLEGAL_OPERATION = 0x506; 
    public static final int IL_ILLEGAL_FILE_VALUE = 0x507; 
    public static final int IL_INVALID_FILE_HEADER = 0x508; 
    public static final int IL_INVALID_PARAM = 0x509; 
    public static final int IL_COULD_NOT_OPEN_FILE = 0x50A; 
    public static final int IL_INVALID_EXTENSION = 0x50B; 
    public static final int IL_FILE_ALREADY_EXISTS = 0x50C; 
    public static final int IL_OUT_FORMAT_SAME = 0x50D; 
    public static final int IL_STACK_OVERFLOW = 0x50E; 
    public static final int IL_STACK_UNDERFLOW = 0x50F; 
    public static final int IL_INVALID_CONVERSION = 0x510; 
    public static final int IL_BAD_DIMENSIONS = 0x511; 
    public static final int IL_FILE_READ_ERROR = 0x512; 
     
    public static final int IL_LIB_GIF_ERROR = 0x5E1; 
    public static final int IL_LIB_JPEG_ERROR = 0x5E2; 
    public static final int IL_LIB_PNG_ERROR = 0x5E3; 
    public static final int IL_LIB_TIFF_ERROR = 0x5E4; 
    public static final int IL_LIB_MNG_ERROR = 0x5E5; 
    public static final int IL_UNKNOWN_ERROR = 0x5FF; 
     
    // Origin Definitions 
    public static final int IL_ORIGIN_SET = 0x600; 
    public static final int IL_ORIGIN_LOWER_LEFT = 0x601; 
    public static final int IL_ORIGIN_UPPER_LEFT = 0x602; 
    public static final int IL_ORIGIN_MODE = 0x603; 
     
    // Format and Type Mode Definitions 
    public static final int IL_FORMAT_SET = 0x610; 
    public static final int IL_FORMAT_MODE = 0x611; 
    public static final int IL_TYPE_SET = 0x612; 
    public static final int IL_TYPE_MODE = 0x613; 
     
    // File definitions 
    public static final int IL_FILE_OVERWRITE = 0x620; 
    public static final int IL_FILE_MODE = 0x621; 
     
    // Palette definitions 
    public static final int IL_CONV_PAL = 0x630; 
     
    // Load fail definitions 
    public static final int IL_DEFAULT_ON_FAIL = 0x632; 
     
    // Key colour definitions 
    public static final int IL_USE_KEY_COLOUR = 0x635; 
    public static final int IL_USE_KEY_COLOR = 0x635; 
     
    // Interlace definitions 
    public static final int IL_SAVE_INTERLACED = 0x639; 
    public static final int IL_INTERLACE_MODE = 0x63A; 
     
    // Quantization definitions 
    public static final int IL_QUANTIZATION_MODE = 0x640; 
    public static final int IL_WU_QUANT = 0x641; 
    public static final int IL_NEU_QUANT = 0x642; 
    public static final int IL_NEU_QUANT_SAMPLE = 0x643; 
     
    // Hints 
    public static final int IL_FASTEST = 0x660; 
    public static final int IL_LESS_MEM = 0x661; 
    public static final int IL_DONT_CARE = 0x662; 
    public static final int IL_MEM_SPEED_HINT = 0x665; 
    public static final int IL_USE_COMPRESSION = 0x666; 
    public static final int IL_NO_COMPRESSION = 0x667; 
    public static final int IL_COMPRESSION_HINT = 0x668; 
     
    // Subimage types 
    public static final int IL_SUB_NEXT = 0x680; 
    public static final int IL_SUB_MIPMAP = 0x681; 
    public static final int IL_SUB_LAYER = 0x682; 
     
    // Compression definitions (mostly for .oil) 
    public static final int IL_COMPRESS_MODE = 0x700; 
    public static final int IL_COMPRESS_NONE = 0x701; 
    public static final int IL_COMPRESS_RLE = 0x702; 
    public static final int IL_COMPRESS_LZO = 0x703; 
    public static final int IL_COMPRESS_ZLIB = 0x704; 
     
    // File format-specific values 
    public static final int IL_TGA_CREATE_STAMP = 0x710; 
    public static final int IL_JPG_QUALITY = 0x711; 
    public static final int IL_PNG_INTERLACE = 0x712; 
    public static final int IL_TGA_RLE = 0x713; 
    public static final int IL_BMP_RLE = 0x714; 
    public static final int IL_SGI_RLE = 0x715; 
    public static final int IL_TGA_ID_STRING = 0x717; 
    public static final int IL_TGA_AUTHNAME_STRING = 0x718; 
    public static final int IL_TGA_AUTHCOMMENT_STRING = 0x719; 
    public static final int IL_PNG_AUTHNAME_STRING = 0x71A; 
    public static final int IL_PNG_TITLE_STRING = 0x71B; 
    public static final int IL_PNG_DESCRIPTION_STRING = 0x71C; 
    public static final int IL_TIF_DESCRIPTION_STRING = 0x71D; 
    public static final int IL_TIF_HOSTCOMPUTER_STRING = 0x71E; 
    public static final int IL_TIF_DOCUMENTNAME_STRING = 0x71F; 
    public static final int IL_TIF_AUTHNAME_STRING = 0x720; 
    public static final int IL_JPG_SAVE_FORMAT = 0x721; 
    public static final int IL_CHEAD_HEADER_STRING = 0x722; 
    public static final int IL_PCD_PICNUM = 0x723; 
     
    // DXTC definitions 
    public static final int IL_DXTC_FORMAT = 0x705; 
    public static final int IL_DXT1 = 0x706; 
    public static final int IL_DXT2 = 0x707; 
    public static final int IL_DXT3 = 0x708; 
    public static final int IL_DXT4 = 0x709; 
    public static final int IL_DXT5 = 0x70A; 
    public static final int IL_DXT_NO_COMP = 0x70B; 
    public static final int IL_KEEP_DXTC_DATA = 0x70C; 
    public static final int IL_DXTC_DATA_FORMAT = 0x70D; 
     
    // Cube map definitions 
    public static final int IL_CUBEMAP_POSITIVEX = 0x400; 
    public static final int IL_CUBEMAP_NEGATIVEX = 0x800; 
    public static final int IL_CUBEMAP_POSITIVEY = 0x1000; 
    public static final int IL_CUBEMAP_NEGATIVEY = 0x2000; 
    public static final int IL_CUBEMAP_POSITIVEZ = 0x4000; 
    public static final int IL_CUBEMAP_NEGATIVEZ = 0x8000; 
     
    // Values 
    public static final int IL_VERSION_NUM = 0xDE2; 
    public static final int IL_IMAGE_WIDTH = 0xDE4; 
    public static final int IL_IMAGE_HEIGHT = 0xDE5; 
    public static final int IL_IMAGE_DEPTH = 0xDE6; 
    public static final int IL_IMAGE_SIZE_OF_DATA = 0xDE7; 
    public static final int IL_IMAGE_BPP = 0xDE8; 
    public static final int IL_IMAGE_BYTES_PER_PIXEL = 0xDE8; 
    public static final int IL_IMAGE_BITS_PER_PIXEL = 0xDE9; 
    public static final int IL_IMAGE_FORMAT = 0xDEA; 
    public static final int IL_IMAGE_TYPE = 0xDEB; 
    public static final int IL_PALETTE_TYPE = 0xDEC; 
    public static final int IL_PALETTE_SIZE = 0xDED; 
    public static final int IL_PALETTE_BPP = 0xDEE; 
    public static final int IL_PALETTE_NUM_COLS = 0xDEF; 
    public static final int IL_PALETTE_BASE_TYPE = 0xDF0; 
    public static final int IL_NUM_IMAGES = 0xDF1; 
    public static final int IL_NUM_MIPMAPS = 0xDF2; 
    public static final int IL_NUM_LAYERS = 0xDF3; 
    public static final int IL_ACTIVE_IMAGE = 0xDF4; 
    public static final int IL_ACTIVE_MIPMAP = 0xDF5; 
    public static final int IL_ACTIVE_LAYER = 0xDF6; 
    public static final int IL_CUR_IMAGE = 0xDF7; 
    public static final int IL_IMAGE_DURATION = 0xDF8; 
    public static final int IL_IMAGE_PLANESIZE = 0xDF9; 
    public static final int IL_IMAGE_BPC = 0xDFA; 
    public static final int IL_IMAGE_OFFX = 0xDFB; 
    public static final int IL_IMAGE_OFFY = 0xDFC; 
    public static final int IL_IMAGE_CUBEFLAGS = 0xDFD; 
     
    public static final int IL_SEEK_SET = 0; 
    public static final int IL_SEEK_CUR = 1; 
    public static final int IL_SEEK_END = 2; 
    public static final int IL_EOF = -1; 
    
    static {
      System.loadLibrary("DevIL");
    	System.loadLibrary("lwjgl-devil");
    }
    
    /**
     * @return true if DevIL has been created
     */
    public static boolean isCreated() {
      return created;
    }     

    public static native void initNativeStubs() throws LWJGLException;
    
//    public static native boolean ilActiveImage(ILuint Number);
//    public static native boolean ilActiveLayer(ILuint Number);
//    public static native boolean ilActiveMipmap(ILuint Number);
//    public static native boolean ilApplyPal(const ILstring FileName);
//    public static native boolean ilApplyProfile(const ILstring InProfile, const ILstring OutProfile);
    public static native void ilBindImage(int image);
//    public static native boolean ilBlit(ILuint Source, ILint DestX, ILint DestY, ILint DestZ, ILuint SrcX, ILuint SrcY, ILuint SrcZ, ILuint Width, ILuint Height, ILuint Depth);
//    public static native void ilClearColour(ILclampf Red, ILclampf Green, ILclampf Blue, ILclampf Alpha);
//    public static native boolean ilClearImage(ILvoid);
//    public static native int ilCloneCurImage(ILvoid);
//    public static native boolean ilCompressFunc(ILenum Mode);
//    public static native boolean ilConvertImage(ILenum DestFormat, ILenum DestType);
//    public static native boolean ilConvertPal(ILenum DestFormat);
//    public static native boolean ilCopyImage(ILuint Src);
//    public static native int ilCopyPixels(ILuint XOff, ILuint YOff, ILuint ZOff, ILuint Width, ILuint Height, ILuint Depth, ILenum Format, ILenum Type, ILvoid *Data);
//    public static native int ilCreateSubImage(ILenum Type, ILuint Num);
//    public static native boolean ilDefaultImage(ILvoid);
    public static void ilDeleteImages(int num, IntBuffer images) {
        BufferChecks.checkDirect(images);
        nilGenImages(num, images, images.position());
    }
    public static native void nilDeleteImages(int num, IntBuffer images, int images_offset);
//    public static native boolean ilDisable(ILenum Mode);
//    public static native boolean ilEnable(ILenum Mode);
//    public static native boolean ilFormatFunc(ILenum Mode);
    public static void ilGenImages(int num, IntBuffer images) {
        BufferChecks.checkDirect(images);
        nilGenImages(num, images, images.position());
    }
    public static native void nilGenImages(int num, IntBuffer images, int images_offset);
//    public static native byte[] ilGetAlpha(ILenum Type);//ILubyte*
//    public static native void ilModAlpha( ILint AlphaValue );
//    public static native void ilSetAlpha( ILuint AlphaValue );
//    public static native boolean ilGetBoolean(ILenum Mode);
//    public static native void ilGetBooleanv(ILenum Mode, ILboolean *Param);
    public static native ByteBuffer ilGetData();
//    public static native int ilGetDXTCData(ILvoid *Buffer, ILuint BufferSize, ILenum DXTCFormat);
    public static native int ilGetError();
    public static native int ilGetInteger(int mode);
//    public static native void ilGetIntegerv(ILenum Mode, ILint *Param);
//    public static native int ilGetLumpPos(ILvoid);
//    public static native byte[] ilGetPalette(ILvoid);//ILubyte*
//    public static native String ilGetString(ILenum StringName);//const ILstring
//    public static native void ilHint(ILenum Target, ILenum Mode);
    public static native void ilInit();
//    public static native boolean ilIsDisabled(ILenum Mode);
//    public static native boolean ilIsEnabled(ILenum Mode);
//    public static native boolean ilIsImage(ILuint Image);
//    public static native boolean ilIsValid(ILenum Type, const ILstring FileName);
//    public static native boolean ilIsValidF(ILenum Type, ILHANDLE File);
//    public static native boolean ilIsValidL(ILenum Type, ILvoid *Lump, ILuint Size);
//    public static native void ilKeyColour(ILclampf Red, ILclampf Green, ILclampf Blue, ILclampf Alpha);
//    public static native boolean ilLoad(ILenum Type, const ILstring FileName);
//    public static native boolean ilLoadF(ILenum Type, ILHANDLE File);
    public static native boolean ilLoadImage(String fileName);
//    public static native boolean ilLoadL(ILenum Type, ILvoid *Lump, ILuint Size);
//    public static native boolean ilLoadPal(const ILstring FileName);
//    public static native boolean ilOriginFunc(ILenum Mode);
//    public static native boolean ilOverlayImage(ILuint Source, ILint XCoord, ILint YCoord, ILint ZCoord);
//    public static native void ilPopAttrib(ILvoid);
//    public static native void ilPushAttrib(ILuint Bits);
//    public static native void ilRegisterFormat(ILenum Format);
//    public static native boolean ilRegisterLoad(const ILstring Ext, IL_LOADPROC Load);
//    public static native boolean ilRegisterMipNum(ILuint Num);
//    public static native boolean ilRegisterNumImages(ILuint Num);
//    public static native void ilRegisterOrigin(ILenum Origin);
//    public static native void ilRegisterPal(ILvoid *Pal, ILuint Size, ILenum Type);
//    public static native boolean ilRegisterSave(const ILstring Ext, IL_SAVEPROC Save);
//    public static native void ilRegisterType(ILenum Type);
//    public static native boolean ilRemoveLoad(const ILstring Ext);
//    public static native boolean ilRemoveSave(const ILstring Ext);
//    public static native void ilResetMemory(ILvoid);
//    public static native void ilResetRead(ILvoid);
//    public static native void ilResetWrite(ILvoid);
//    public static native boolean ilSave(ILenum Type, const ILstring FileName);
//    public static native int ilSaveF(ILenum Type, ILHANDLE File);
//    public static native boolean ilSaveImage(const ILstring FileName);
//    public static native int ilSaveL(ILenum Type, ILvoid *Lump, ILuint Size);
//    public static native boolean ilSavePal(const ILstring FileName);
//    public static native boolean ilSetData(ILvoid *Data);
//    public static native boolean ilSetDuration(ILuint Duration);
//    public static native void ilSetInteger(ILenum Mode, ILint Param);
//    public static native void ilSetMemory(mAlloc, mFree);
//    public static native void ilSetPixels(ILint XOff, ILint YOff, ILint ZOff, ILuint Width, ILuint Height, ILuint Depth, ILenum Format, ILenum Type, ILvoid *Data);
//    public static native void ilSetRead(fOpenRProc, fCloseRProc, fEofProc, fGetcProc, fReadProc, fSeekRProc, fTellRProc);
//    public static native void ilSetString(ILenum Mode, const char *String);
//    public static native void ilSetWrite(fOpenWProc, fCloseWProc, fPutcProc, fSeekWProc, fTellWProc, fWriteProc);
//    public static native void ilShutDown(ILvoid);
//    public static native boolean ilTexImage(ILuint Width, ILuint Height, ILuint Depth, ILubyte Bpp, ILenum Format, ILenum Type, ILvoid *Data);
//    public static native boolean ilTypeFunc(ILenum Mode);

		/**
		 * 
		 */
    public static void create() throws LWJGLException {
    	if (!created) {
    		IL.initNativeStubs();
        IL.ilInit();
    		created = true;
    	}
    }

//    public static native boolean ilLoadData(const ILstring FileName, ILuint Width, ILuint Height, ILuint Depth, ILubyte Bpp);
//    public static native boolean ilLoadDataF(ILHANDLE File, ILuint Width, ILuint Height, ILuint Depth, ILubyte Bpp);
//    public static native boolean ilLoadDataL(ILvoid *Lump, ILuint Size, ILuint Width, ILuint Height, ILuint Depth, ILubyte Bpp);
//    public static native boolean ilSaveData(const ILstring FileName);

//    public static native boolean ilLoadFromJpegStruct(ILvoid* JpegDecompressorPtr);
//    public static native boolean ilSaveFromJpegStruct(ILvoid* JpegCompressorPtr);
}