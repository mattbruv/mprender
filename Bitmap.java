import java.util.Arrays;

/**
 * Stores a set of pixels in a component-based format.
 * The component-based format stores colors as follows:
 *
 * Byte 0: Alpha
 * Byte 1: Blue
 * Byte 2: Green
 * Byte 3: Red
 *
 * This format is fast, compact, and ideal for software rendering.
 * It has the following key advantages:
 * - Entire images can be copied to the screen with a single call to
 * System.arrayCopy. (If the screen is not in ABGR pixel format, it requires
 * some conversion. However, the conversion is typically quick and simple).
 * - Per component operations, such as lighting, can be performed cheaply
 * without any
 * pixel format converison.
 *
 * This class is primarily intended to be a high-performance image storing
 * facility for software rendering. As such, there are points where ease of
 * use is compromised for the sake of performance. If you need to store and
 * use images outside of a software renderer, it is recommended that you use
 * Java's standard image classes instead.
 */
public class Bitmap {
    /** The width, in pixels, of the image */
    private final int m_width;
    /** The height, in pixels, of the image */
    private final int m_height;
    /** Every pixel component in the image */
    private final byte m_components[];

    /** Basic getter */
    public int GetWidth() {
        return m_width;
    }

    /** Basic getter */
    public int GetHeight() {
        return m_height;
    }

    /**
     * Creates and initializes a Bitmap.
     *
     * @param width  The width, in pixels, of the image.
     * @param height The height, in pixels, of the image.
     */
    public Bitmap(int width, int height) {
        m_width = width;
        m_height = height;
        m_components = new byte[m_width * m_height * 4];
    }

    /**
     * Sets every pixel in the bitmap to a specific shade of grey.
     */
    public void Clear(byte shade) {
        Arrays.fill(m_components, shade);
    }

    /**
     * Sets the pixel at (x, y) to the color specified by (a,b,g,r).
     */
    public void DrawPixel(int x, int y, byte a, byte b, byte g, byte r) {
        int index = (x + y * m_width) * 4;
        m_components[index] = a;
        m_components[index + 1] = b;
        m_components[index + 2] = g;
        m_components[index + 3] = r;
    }

    /**
     * Copies the Bitmap into a BGR byte array.
     */
    public void CopyToByteArray(byte[] dest) {
        for (int i = 0; i < m_width * m_height; i++) {
            dest[i * 3] = m_components[i * 4 + 1];
            dest[i * 3 + 1] = m_components[i * 4 + 2];
            dest[i * 3 + 2] = m_components[i * 4 + 3];
        }
    }
}