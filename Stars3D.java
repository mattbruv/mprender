public class Stars3D {
    private final float m_spread;
    private final float m_speed;
    private final float[] m_starX;
    private final float[] m_starY;
    private final float[] m_starZ;

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

    private void InitStar(int index) {
        m_starX[index] = 2 * ((float) Math.random() - 0.5f) * m_spread;
        m_starY[index] = 2 * ((float) Math.random() - 0.5f) * m_spread;
        m_starZ[index] = ((float) Math.random() + 0.00001f) * m_spread;
    }

    public void updateAndRender(Bitmap target, float delta) {
        target.Clear((byte) 0x00);

        float halfWidth = target.getWidth() / 2.0f;
        float halfHeight = target.getHeight() / 2.0f;

        for (int i = 0; i < m_starX.length; i++) {
            m_starZ[i] -= delta * m_speed;

            if (m_starZ[i] <= 0) {
                InitStar(i);
            }

            int x = (int) ((m_starX[i] / m_starZ[i]) * halfWidth + halfWidth);
            int y = (int) ((m_starY[i] / m_starZ[i]) * halfHeight + halfHeight);
            System.out.println(x + " test ");

            if (x < 0 || x >= target.getWidth() ||
                    (y < 0 || y >= target.getHeight())) {
                InitStar(i);
            } else {
                target.DrawPixel(x, y, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff);
            }
        }
    }
}