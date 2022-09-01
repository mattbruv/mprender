
public class Main {
    public static void main(String[] args) {
        Display display = new Display(800, 600, "Software Renderer");

        RenderContext target = display.GetFrameBuffer();
        Stars3D stars = new Stars3D(200, 64.0f, 20.0f);

        Vertex minYVert = new Vertex(100, 100);
        Vertex midYVert = new Vertex(150, 200);
        Vertex maxYVert = new Vertex(80, 300);

        long previousTime = System.nanoTime();

        while (true) {
            long currentTime = System.nanoTime();
            float delta = (float) ((currentTime - previousTime) / 1000000000.0);
            previousTime = currentTime;

            // stars.UpdateAndRender(target, delta);
            target.Clear((byte) 0x00);

            target.ScanConvertTriangle(minYVert, midYVert, maxYVert, 0);
            target.FillShape(100, 300);

            display.SwapBuffers();
        }
    }

}