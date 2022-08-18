/**
 * Represents a 3D Star field that can be rendered into an image.
 */
public class Stars3D {
    /** How much the stars are spread out in 3D space, on average. */
    private final float m_spread;
    /** How quickly the stars move towards the camera */
    private final float m_speed;

    /** The star positions on the X axis */
    private final float m_starX[];
    /** The star positions on the Y axis */
    private final float m_starY[];
    /** The star positions on the Z axis */
    private final float m_starZ[];

    /**
     * Creates a new 3D star field in a usable state.
     *
     * @param numStars The number of stars in the star field
     * @param spread   How much the stars spread out, on average.
     * @param speed    How quickly the stars move towards the camera
     */
    public Stars3D(int numStars, float spread, float speed) {
        m_spread = spread;
        m_speed = speed;

        m_starX = new float[numStars];
        m_starY = new float[numStars];
        m_starZ = new float[numStars];

        for (int i = 0; i < m_starX.length; i++) {
            InitStar(i);
        }
    }

    /**
     * Initializes a star to a new pseudo-random location in 3D space.
     *
     * @param i The index of the star to initialize.
     */
    private void InitStar(int i) {
        // The random values have 0.5 subtracted from them and are multiplied
        // by 2 to remap them from the range (0, 1) to (-1, 1).
        m_starX[i] = 2 * ((float) Math.random() - 0.5f) * m_spread;
        m_starY[i] = 2 * ((float) Math.random() - 0.5f) * m_spread;
        // For Z, the random value is only adjusted by a small amount to stop
        // a star from being generated at 0 on Z.
        m_starZ[i] = ((float) Math.random() + 0.00001f) * m_spread;
    }

    /**
     * Updates every star to a new position, and draws the starfield in a
     * bitmap.
     *
     * @param target The bitmap to render to.
     * @param delta  How much time has passed since the last update.
     */
    public void UpdateAndRender(Bitmap target, float delta) {
        // Stars are drawn on a black background
        target.Clear((byte) 0x00);

        float halfWidth = target.GetWidth() / 2.0f;
        float halfHeight = target.GetHeight() / 2.0f;
        for (int i = 0; i < m_starX.length; i++) {
            // Update the Star.

            // Move the star towards the camera which is at 0 on Z.
            m_starZ[i] -= delta * m_speed;

            // If star is at or behind the camera, generate a new position for
            // it.
            if (m_starZ[i] <= 0) {
                InitStar(i);
            }

            // Render the Star.

            // Multiplying the position by (size/2) and then adding (size/2)
            // remaps the positions from range (-1, 1) to (0, size)
            int x = (int) ((m_starX[i] / m_starZ[i]) * halfWidth + halfWidth);
            int y = (int) ((m_starY[i] / m_starZ[i]) * halfHeight + halfHeight);

            // If the star is not within range of the screen, then generate a
            // new position for it.
            if (x < 0 || x >= target.GetWidth() ||
                    (y < 0 || y >= target.GetHeight())) {
                InitStar(i);
            } else {
                // Otherwise, it is safe to draw this star to the screen.
                target.DrawPixel(x, y, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF);
            }
        }
    }
}