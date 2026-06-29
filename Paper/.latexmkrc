# Sagt latexmk, wie es das aux-Verzeichnis an makeglossaries übergibt
add_cus_dep('acn', 'acr', 0, 'run_makeglossaries');
sub run_makeglossaries {
    my ($base, $ext) = @_;
    my $dir = '';
    # Extrahiert den Verzeichnispfad, falls -aux-directory genutzt wird
    if ( $base =~ /^(.*\/)([^\/]+)$/ ) {
        $dir = $1;
        $base = $2;
    }
    # Führt makeglossaries mit dem -d Flag für das korrekte Verzeichnis aus
    if ($dir eq '') {
        return system("makeglossaries \"$base\"");
    } else {
        return system("makeglossaries -d \"$dir\" \"$base\"");
    }
}