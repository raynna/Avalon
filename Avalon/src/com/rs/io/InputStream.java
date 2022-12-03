package com.rs.io;

import com.rs.game.player.Player;
import com.rs.utils.StringUtilities;

public final class InputStream extends Stream {

	private static final int[] BIT_MASK = { 0, 0x1, 0x3, 0x7, 0xf, 0x1f, 0x3f, 0x7f, 0xff, 0x1ff, 0x3ff, 0x7ff, 0xfff,
			0x1fff, 0x3fff, 0x7fff, 0xffff, 0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff,
			0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, -1 };

	public InputStream(byte[] buffer) {
		this.buffer = buffer;
		this.length = buffer.length;
	}

	public void initBitAccess() {
		bitPosition = offset * 8;
	}

	public void finishBitAccess() {
		offset = (bitPosition + 7) / 8;
	}

	public final int readBits(int count) {
		int bufferPosition = this.bitPosition >> 3;
		int bitOffset = 8 - (this.bitPosition & 0x7);
		this.bitPosition += count;
		int value = 0;
		for (; count > bitOffset; bitOffset = 8) {
			value += ((this.getBuffer()[bufferPosition++] & BIT_MASK[bitOffset]) << count - bitOffset);
			count -= bitOffset;
		}
		if (count != bitOffset)
			value += (this.getBuffer()[bufferPosition] >> bitOffset - count & BIT_MASK[count]);
		else
			value += (BIT_MASK[bitOffset] & this.getBuffer()[bufferPosition]);
		return value;
	}

	public int getRemaining() {
		return offset < length ? length - offset : 0;
	}

	public void skip(int length) {
		offset += length;
	}

	public int readByte() {
		return getRemaining() > 0 ? buffer[offset++] : 0;
	}

	public int peekByte() {
		return getRemaining() > 0 ? buffer[offset] : 0;
	}

	public void readBytes(byte buffer[]) {
		readBytes(buffer, 0, buffer.length);
	}

	public void readBytes(byte buffer[], int off, int len) {
		for (int k = off; k < len + off; k++) {
			buffer[k] = (byte) readByte();
		}
	}

	public int readPacket(Player player) {
		int id = 0xff & readUnsignedByte() - player.getIsaacKeyPair().inKey().getNextValue();
		if (id < 128)
			return id;
		return (id - 128 << 8) + (readUnsignedByte() - player.getIsaacKeyPair().inKey().getNextValue());
	}

	public String readString() {
		int startpos = offset;
		while (offset < buffer.length && buffer[offset++] != 0) {
		}
		int strlen = offset - startpos - 1;
		if (strlen <= 0)
			return "";
		return StringUtilities.decodeString(buffer, startpos, strlen);
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String readVersionedString() { // aka JAG string as you called it
		return readVersionedString((byte) 0);
	}

	public String readVersionedString(byte versionNumber) {
		if (readByte() != versionNumber)
			throw new IllegalStateException("Bad string version number!");
		return readString();
	}

	public String readNullString() {
		if (peekByte() == 0) {
			skip(1);
			return null;
		}
		return readString();
	}

	public int read24BitInt() {
		return (readUnsignedByte() << 16) + (readUnsignedByte() << 8) + (readUnsignedByte());
	}

	public int readSmart2() {
		int i = 0;
		int i_33_ = readUnsignedSmart();
		while (i_33_ == 32767) {
			i_33_ = readUnsignedSmart();
			i += 32767;
		}
		i += i_33_;
		return i;
	}

	public int readUnsignedByte() {
		return readByte() & 0xff;
	}

	public int readByte128() {
		return (byte) (readByte() - 128);
	}

	public int readByteC() {
		return (byte) -readByte();
	}

	public int read128Byte() {
		return (byte) (128 - readByte());
	}

	public int readUnsignedByte128() {
		return readUnsignedByte() - 128 & 0xff;
	}

	public int readUnsignedByteC() {
		return -readUnsignedByte() & 0xff;
	}

	public int readUnsigned128Byte() {
		return 128 - readUnsignedByte() & 0xff;
	}

	public int readShortLE() {
		int i = readUnsignedByte() + (readUnsignedByte() << 8);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readShort128() {
		int i = (readUnsignedByte() << 8) + (readByte() - 128 & 0xff);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readShortLE128() {
		int i = (readByte() - 128 & 0xff) + (readUnsignedByte() << 8);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int read128ShortLE() {
		int i = (128 - readByte() & 0xff) + (readUnsignedByte() << 8);
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readShort() {
		int i = (readUnsignedByte() << 8) + readUnsignedByte();
		if (i > 32767) {
			i -= 0x10000;
		}
		return i;
	}

	public int readUnsignedShortLE() {
		return readUnsignedByte() + (readUnsignedByte() << 8);
	}

	public int readUnsignedShort() {
		return (readUnsignedByte() << 8) + readUnsignedByte();
	}

	public int readSmart() {
		int var2 = peekByte() & 0xff;
		if (var2 < 128) {
			return readUnsignedByte();
		}
		return readUnsignedShort() - 32768;
	}

	public int readSmart3() {
		int var2 = peekByte() & 0xff;
		if (var2 < 128) {
			return readUnsignedByte() - 64;
		}
		return readUnsignedShort() - 49152;
	}

	public int readBigSmart() {
		if ((peekByte() ^ 0xffffffff) <= -1) {
			int value = readUnsignedShort();
			if (value == 32767) {
				return -1;
			}
			return value;
		}
		return readInt() & 0x7fffffff;
	}

	public int readDecoratedSmart() {
		int var2 = peekByte() & 0xff;
		if (var2 < 128) {
			return readUnsignedByte() - 1;
		}
		return readUnsignedShort() - 32769;
	}

	public int readUnsignedShort128() {
		return (readUnsignedByte() << 8) + (readByte() - 128 & 0xff);
	}

	public int readUnsignedShortLE128() {
		return (readByte() - 128 & 0xff) + (readUnsignedByte() << 8);
	}

	public int readInt() {
		return (readUnsignedByte() << 24) + (readUnsignedByte() << 16) + (readUnsignedByte() << 8) + readUnsignedByte();
	}

	public int readIntV1() {
		return (readUnsignedByte() << 8) + readUnsignedByte() + (readUnsignedByte() << 24) + (readUnsignedByte() << 16);
	}

	public int readIntV2() {
		return (readUnsignedByte() << 16) + (readUnsignedByte() << 24) + readUnsignedByte() + (readUnsignedByte() << 8);
	}

	public int readIntLE() {
		return readUnsignedByte() + (readUnsignedByte() << 8) + (readUnsignedByte() << 16) + (readUnsignedByte() << 24);
	}

	public long readLong() {
		long l = readInt() & 0xffffffffL;
		long l1 = readInt() & 0xffffffffL;
		return (l << 32) + l1;
	}

	public int readUnsignedSmart() {
		int i = peekByte() & 0xff;
		if (i >= 128) {
			return -32768 + readUnsignedShort();
		}
		return readUnsignedByte();
	}

	public int readCustomInt() {
		return (readUnsignedByte() << 24) + (readUnsignedByte() << 16) + (readUnsignedByte() << 8) + readUnsignedByte()
				+ 128;
	}

	public long readDynamic(int byteCount) {
		if (--byteCount < 0 || byteCount > 7) {
			throw new IllegalArgumentException();
		}
		int bitOffset = byteCount * 8;
		long l = 0L;
		for (/**/; bitOffset >= 0; bitOffset -= 8) {

			l |= (0xffL & readByte()) << bitOffset;
		}
		return l;
	}

}