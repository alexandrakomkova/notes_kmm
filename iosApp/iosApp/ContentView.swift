import SwiftUI
import shared
import Foundation

/*
struct ContentView: View {
    var body: some View {
        ComposeView()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
*/
struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
    
}

struct ContentView: View {
    var body: some View {
        // Text("hello")
        ComposeView().ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}
